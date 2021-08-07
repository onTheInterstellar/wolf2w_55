package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.domain.Region;
import cn.wolfcode.wolf2w.query.RegionQuery;
import cn.wolfcode.wolf2w.service.IRegionService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("region")
public class RegionController {

    @Autowired
    private IRegionService regionService;

    @RequestMapping("/list")
    private String list(ModelMap map, @ModelAttribute("qo") RegionQuery qo) {

        Page<Region> page = regionService.QueryPage(qo);
        map.put("page", page);
        return "/region/list.html";

    }

}
