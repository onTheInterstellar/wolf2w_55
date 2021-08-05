package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;

public interface IUserInfoService extends IService<UserInfo> {

    Object checkPhone(String phoneNumber);

    void sendVerifyCode(String phone);

    void register(String phone, String nickname, String password, String rpassword, String verifyCode);

    HashMap<String, Object> login(String username, String password);
}
