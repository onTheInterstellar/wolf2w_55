package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.domain.Destination;
import cn.wolfcode.wolf2w.domain.Region;
import cn.wolfcode.wolf2w.service.IDestinationService;
import cn.wolfcode.wolf2w.service.IRegionService;
import cn.wolfcode.wolf2w.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("destinations")
public class DestinationController {

    @Autowired
    private IDestinationService destinationService;

    @Autowired
    private IRegionService regionService;

    @GetMapping("/hotRegion")
    private Object list() {

        List<Region> list = regionService.QueryHotRegion();
        return JsonResult.success(list);

    }

    //destinations/search?regionId=3

    @GetMapping("/search")
    private Object search(Long regionId) {

        List<Destination> list = destinationService.QueryHotRegion(regionId);
        return JsonResult.success(list);

    }

}
