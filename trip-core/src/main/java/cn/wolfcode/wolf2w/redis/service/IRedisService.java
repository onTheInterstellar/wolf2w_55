package cn.wolfcode.wolf2w.redis.service;


public interface IRedisService {
    void saveVerify(String phone, String verifyCode);
}
