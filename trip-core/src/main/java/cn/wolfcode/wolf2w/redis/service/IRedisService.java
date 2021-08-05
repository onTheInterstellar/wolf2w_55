package cn.wolfcode.wolf2w.redis.service;


import cn.wolfcode.wolf2w.domain.UserInfo;

public interface IRedisService {
    void saveVerify(String phone, String verifyCode);

    String getVerify(String phone);

    String getToken(UserInfo userInfo);

}
