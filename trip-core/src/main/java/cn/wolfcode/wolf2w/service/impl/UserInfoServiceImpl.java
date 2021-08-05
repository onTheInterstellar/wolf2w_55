package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.UserInfo;
import cn.wolfcode.wolf2w.mapper.UserInfoMapper;
import cn.wolfcode.wolf2w.redis.service.IRedisService;
import cn.wolfcode.wolf2w.service.IUserInfoService;
import cn.wolfcode.wolf2w.util.AssertUtil;
import cn.wolfcode.wolf2w.util.Consts;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Autowired
    private IRedisService redisService;


    @Override
    public void register(String phone, String nickname, String password, String rpassword, String verifyCode) {
        //判断参数是否为空
        AssertUtil.hasText(phone, "手机号不能为空");
        AssertUtil.hasText(nickname, "昵称不能为空");
        AssertUtil.hasText(password, "密码不能为空");
        AssertUtil.hasText(rpassword, "重复密码不能为空");
        AssertUtil.hasText(verifyCode, "验证码不能为空");

        //判断手机格式是否正确, 是否存在
        AssertUtil.checkPhone(checkPhone(phone), "手机号码已经注册过");

        //判断两次密码是否一致
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
                .append(", 有效时间是:").append(Consts.VERIFY_CODE_VAI_TIME).append(" 分钟, 请勿向他人泄露.");
        //保存验证码
        redisService.saveVerify(phone, verifyCode);

    }
}
