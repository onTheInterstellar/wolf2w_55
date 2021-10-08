package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.service.IBannerService;
import cn.wolfcode.wolf2w.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 横幅控制层
*/
@RestController
@RequestMapping("member/token/getCurrentUser")
public class CurrentUserController {

    @Autowired
    private IBannerService bannerService;

    @GetMapping
    public Object currentUser(){

        return JsonResult.success("1");
    }



}
