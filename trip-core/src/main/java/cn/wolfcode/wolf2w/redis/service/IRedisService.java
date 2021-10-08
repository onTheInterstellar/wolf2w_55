package cn.wolfcode.wolf2w.redis.service;


import cn.wolfcode.wolf2w.domain.UserInfo;

import java.util.Map;

public interface IRedisService {

    /**
     * 将验证码把保存在redis中
     * @param phone
     * @param verifyCode
     */
    void saveVerify(String phone, String verifyCode);


    /**
     * 通过phone过去验证码
     * @param phone
     * @return
     */
    String getVerify(String phone);

    /**
     * 将userInfo对象存入redis
     * 返回token
     * @param userInfo
     * @return
     */
    String getToken(UserInfo userInfo);

    /**
     * 通过token或取redis中的userInfo
     * @param token
     * @return
     */
    UserInfo getUserInfoByToken(String token);

    /**
     * 设置访客
     * @param vToken
     * @param uId
     */
    void setVisitor(String vToken, Long uId);

    /**
     * 通过id查找访客
     * @param id
     * @return
     */
    Map<String, Object> getVisitorsByOwnerId(Long id);
}
