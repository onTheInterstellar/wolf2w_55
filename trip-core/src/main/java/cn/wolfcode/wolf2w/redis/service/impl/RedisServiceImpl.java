package cn.wolfcode.wolf2w.redis.service.impl;

import cn.wolfcode.wolf2w.redis.service.IRedisService;
import cn.wolfcode.wolf2w.util.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements IRedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void saveVerify(String phone, String verifyCode) {
        String verifyKey = Consts.VERIFY_CODE + ":" +phone;
        redisTemplate.opsForValue().set(verifyKey, verifyCode, Consts.VERIFY_CODE_VAI_TIME, TimeUnit.SECONDS);
        //做测试用, 弄完之后删除
        System.out.println(verifyCode);
    }

}
