package cn.wolfcode.wolf2w.redis.service.impl;

import cn.wolfcode.wolf2w.domain.UserInfo;
import cn.wolfcode.wolf2w.redis.service.IRedisService;
import cn.wolfcode.wolf2w.redis.utils.RedisKeys;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements IRedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public UserInfo getUserInfoByToken(String token) {
        //判断token是否为空
        if (!StringUtils.hasText(token))
            return null;
        // 获取key
        String tokenKey = RedisKeys.USER_LONGIN_TOKEN.join(token);

        // 判断key是否过期
        if (redisTemplate.hasKey(tokenKey)) {
            // 获取userInfo
            String userInfoStr = redisTemplate.opsForValue().get(tokenKey);
            redisTemplate.expire(tokenKey, Duration.ofMinutes(RedisKeys.USER_LONGIN_TOKEN.getTime()));
            // 转成对象
            UserInfo userInfo = JSON.parseObject(userInfoStr, UserInfo.class);
            return  userInfo;
        }

        return null;
    }

    @Override
    public String getToken(UserInfo userInfo) {

        //以uuid作为区分
        String tokenElement = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set(RedisKeys.USER_LONGIN_TOKEN.join(tokenElement),
                JSON.toJSONString(userInfo), RedisKeys.USER_LONGIN_TOKEN.getTime() * 60L, TimeUnit.SECONDS);
        return tokenElement;
    }


    @Override
    public String getVerify(String phone) {
        String verifyCode = redisTemplate.opsForValue().get(RedisKeys.REGISTER_VERIFY_CODE.join(phone));
        return verifyCode;
    }


    @Override
    public void saveVerify(String phone, String verifyCode) {
        //String verifyKey = Consts.VERIFY_CODE + ":" +phone;
        // 针对key进行优化
        redisTemplate.opsForValue().set(RedisKeys.REGISTER_VERIFY_CODE.join(phone),
                verifyCode, RedisKeys.REGISTER_VERIFY_CODE.getTime() * 60L, TimeUnit.SECONDS);
    }

}
