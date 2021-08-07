package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.annotation.RequireLogin;
import cn.wolfcode.wolf2w.domain.UserInfo;
import cn.wolfcode.wolf2w.redis.service.IRedisService;
import cn.wolfcode.wolf2w.service.IUserInfoService;
import cn.wolfcode.wolf2w.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@RequestMapping("users")
public class UserInfoController {

    @Autowired
    private IRedisService redisService;

    @Autowired
    private IUserInfoService userInfoService;

    @RequireLogin
    @GetMapping("/currentUser")
    public Object currentUser(HttpServletRequest request) {

        String token = request.getHeader("token");
        UserInfo userInfo = redisService.getUserInfoByToken(token);
        return JsonResult.success(userInfo);

    }

    @GetMapping
    public Object detail(Long id) {
        return userInfoService.getById(id);
    }

    @PostMapping("/login")
    private Object login(String username, String password) {

        HashMap<String, Object> map = userInfoService.login(username, password);
        //new HashMap<>();
        return JsonResult.success(map);
    }


    @PostMapping("/register")
    private Object register(String phone, String nickname, String password, String rpassword, String verifyCode) {

        userInfoService.register(phone,nickname,password,rpassword,verifyCode);

        return JsonResult.success();
    }


    @GetMapping("/checkPhone")
    public Object checkPhone(String phone) {
        return userInfoService.checkPhone(phone) != null;
    }

    @GetMapping("/sendVerifyCode")
    public Object sendVerifyCode(String phone) {
        userInfoService.sendVerifyCode(phone);
        return JsonResult.success("验证码发送成功");
    }

}
