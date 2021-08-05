package cn.wolfcode.wolf2w.redis.service.impl;

import cn.wolfcode.wolf2w.domain.UserInfo;
import cn.wolfcode.wolf2w.redis.service.IRedisService;
import cn.wolfcode.wolf2w.redis.utils.RedisKeys;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements IRedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * 将userInfo对象存入redis
     * 返回token
     * @param userInfo
     * @return
     */
    @Override
    public String getToken(UserInfo userInfo) {

        //以uuid作为区分
        String tokenElement = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set(RedisKeys.USER_LONGIN_TOKEN.join(tokenElement),
                JSON.toJSONString(userInfo), RedisKeys.USER_LONGIN_TOKEN.getTime() * 60L, TimeUnit.SECONDS);
        return tokenElement;
    }

    /**
     * 通过phone过去验证码
     * @param phone
     * @return
     */
    @Override
    public String getVerify(String phone) {
        String verifyCode = redisTemplate.opsForValue().get(RedisKeys.REGISTER_VERIFY_CODE.join(phone));
        return verifyCode;
    }

    /**
     * 将验证码把保存在redis中
     * @param phone
     * @param verifyCode
     */
    @Override
    public void saveVerify(String phone, String verifyCode) {
        //String verifyKey = Consts.VERIFY_CODE + ":" +phone;
        // 针对key进行优化
        redisTemplate.opsForValue().set(RedisKeys.REGISTER_VERIFY_CODE.join(phone),
                verifyCode, RedisKeys.REGISTER_VERIFY_CODE.getTime() * 60L, TimeUnit.SECONDS);
    }

}
