package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.domain.Banner;
import cn.wolfcode.wolf2w.service.IBannerService;
import cn.wolfcode.wolf2w.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* 横幅控制层
*/
@RestController
@RequestMapping("banners")
public class BannerController {

    @Autowired
    private IBannerService bannerService;

    @GetMapping("/travel")
    public Object travel(){
        List<Banner> banners = bannerService.getBannersType(Banner.TYPE_TRAVEL);
        return JsonResult.success(banners);
    }

    @GetMapping("/strategy")
    public Object strategy(){
        List<Banner> banners = bannerService.getBannersType(Banner.TYPE_STRATEGY);
        return JsonResult.success(banners);
    }

}
