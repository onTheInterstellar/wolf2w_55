package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.UserInfo;
import cn.wolfcode.wolf2w.exception.LogicException;
import cn.wolfcode.wolf2w.mapper.UserInfoMapper;
import cn.wolfcode.wolf2w.redis.service.IRedisService;
import cn.wolfcode.wolf2w.service.IUserInfoService;
import cn.wolfcode.wolf2w.util.AssertUtil;
import cn.wolfcode.wolf2w.util.Consts;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.UUID;

@Service
@Transactional
@ConfigurationProperties(prefix = "sms")
@Getter
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {


    private String gateway;

    private String appKey;

    @Autowired
    private IRedisService redisService;

    @Override
    public HashMap<String, Object> login(String username, String password) {

        // 检查数据库中用户是否存在
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", username).eq("password", password);
        UserInfo userInfo = super.getOne(wrapper);
        if (userInfo == null) {
            throw new LogicException("登录失败, 用户名或者密码错误");
        }

        //创建token
        //将以token为key 和 user为value 存入数据库
        String token = redisService.getToken(userInfo);

        HashMap<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("user", userInfo);

        return data;
    }

    @Override
    public void register(String phone, String nickname, String password, String rpassword, String verifyCode) {
        //判断参数是否为空
        AssertUtil.hasText(phone, "手机号不能为空");
        AssertUtil.hasText(nickname, "昵称不能为空");
        AssertUtil.hasText(password, "密码不能为空");
        AssertUtil.hasText(rpassword, "重复的密码不能为空");
        AssertUtil.hasText(verifyCode, "验证码不能为空");

        //判断手机格式是否正确
        AssertUtil.checkPhoneFormat(phone, "手机号码格式不正确");
        //判断手机号是否存在
        AssertUtil.checkPhone(this.checkPhone(phone), "手机号码已经注册过");

        //判断两次密码是否一致
        AssertUtil.comparedPhone(password, rpassword, "两次输入的密码不一致");

        //判断验证码是否一致
        if (!verifyCode.equalsIgnoreCase(redisService.getVerify(phone))) {
            throw new LogicException("验证码已过期或验证码错误");
        }

        // 走到这里说明上面的验证都通过, 即可以封装数据
        UserInfo userInfo = new UserInfo();
        userInfo.setHeadImgUrl("images/default02.jpg");
        userInfo.setPhone(phone);
        userInfo.setNickname(nickname);
        userInfo.setPassword(password);
        userInfo.setState(UserInfo.STATE_NORMAL);

        //保存数据
        super.save(userInfo);


    }

    @Override
    public Object checkPhone(String phoneNumber) {

        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phoneNumber);
        return super.getOne(queryWrapper);
    }

    @Override
    public void sendVerifyCode(String phone) {
        //获取验证码
        String verifyCode = UUID.randomUUID().toString()
                .replace("-", "").substring(0, 4);
        // 拼接字符串
        StringBuilder message = new StringBuilder(80);
        message.append("你好!欢迎注册骆窝窝.你的短信验证码是: ").append(verifyCode)
                .append(", 有效时间是:").append(Consts.VERIFY_CODE_VAI_TIME).append(" 分钟, 请勿向他人泄露!");

        // 真实发送短信
        String appKey = "1f5921e1a61866d5c82b38a9381ea14f";
        String url = "https://way.jd.com/chuangxin/dxjk?mobile={phoneNumber}&content=【创信】{message}&appkey={0}";

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1,new StringHttpMessageConverter(StandardCharsets.UTF_8));

        String result = restTemplate.getForObject(url, String.class, phone, message, appKey);
        System.out.println(result);
        if (!result.contains("Success")) {
            throw new LogicException("验证码发送失败!");
        }

        //保存验证码
        redisService.saveVerify(phone, verifyCode);

    }
}
