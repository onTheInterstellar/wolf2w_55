package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserInfoController {

    @Autowired
    private IUserInfoService userInfoService;


    @GetMapping
    public Object detail(Long id) {
        return userInfoService.getById(id);
    }


    @GetMapping("/checkPhone")
    public Object checkPhone(String phone) {
        return userInfoService.checkPhone(phone) == null;
    }


}
