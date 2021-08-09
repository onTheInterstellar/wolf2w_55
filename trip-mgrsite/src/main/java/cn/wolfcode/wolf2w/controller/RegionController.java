package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.domain.Destination;
import cn.wolfcode.wolf2w.domain.Region;
import cn.wolfcode.wolf2w.query.RegionQuery;
import cn.wolfcode.wolf2w.service.IDestinationService;
import cn.wolfcode.wolf2w.service.IRegionService;
import cn.wolfcode.wolf2w.util.JsonResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("region")
public class RegionController {

    @Autowired
    private IRegionService regionService;

    @Autowired
    private IDestinationService destinationService;

    @RequestMapping("/list")
    private String list(ModelMap map, @ModelAttribute("qo") RegionQuery qo) {

        Page<Region> page = regionService.QueryPage(qo);
        List<Destination> dests = destinationService.list();

        map.put("page", page);
        map.put("dests", dests);
        return "/region/list.html";

    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    private Object saveOrUpdate(Region region) {

        return regionService.saveOrUpdate(region) ? JsonResult.success()
                :JsonResult.defaultError();

    }

    @GetMapping("/getDestByRegionId")
    @ResponseBody
    private Object getDestByRegionId(Long rid) {

        Region region = regionService.getById(rid);
        List<Long> ids = region.parseRefIds();
        return destinationService.listByIds(ids);

    }

    @GetMapping("/changeHotValue")
    @ResponseBody
    private Object changeHotValue(boolean hot, Long id) {

        regionService.changeHotValue(hot, id);
        return regionService.changeHotValue(hot, id) ? JsonResult.success()
                :JsonResult.defaultError();

    }

    @GetMapping("/delete")
    @ResponseBody
    private Object delete(Long id) {
        return regionService.removeById(id) ? JsonResult.success()
                :JsonResult.defaultError();

    }


}
