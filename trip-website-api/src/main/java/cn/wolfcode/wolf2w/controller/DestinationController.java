package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.domain.*;
import cn.wolfcode.wolf2w.mapper.StrategyContentMapper;
import cn.wolfcode.wolf2w.service.*;
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

    @Autowired
    private IStrategyCatalogService catalogService;

    @Autowired
    private IStrategyService strategyService;

    @Autowired
    private StrategyContentMapper contentMapper;

    @Autowired
    private ITravelService travelService;

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

    @GetMapping("/toasts")
    private Object toasts(Long destId) {

        List<Destination> list = destinationService.queryToasts(destId);
        return JsonResult.success(list);

    }

    @GetMapping("/catalogs")
    private Object catalogs(Long destId) {

        List<StrategyCatalog> list = catalogService.queryCatalogs(destId);
        return JsonResult.success(list);

    }

    @GetMapping("/strategies")
    private Object strategies(Long destId) {

        List<Strategy> list = strategyService.queryStrategiesById(destId);
        return JsonResult.success(list);

    }

    @GetMapping("/travels/viewnumTop3")
    private Object viewnumTop3(Long destId) {
        return JsonResult.success(travelService.viewnumTop3(destId));
    }

    @GetMapping("/strategies/viewnumTop3")
    private Object strategiesViewnumTop3(Long destId) {
        return JsonResult.success(strategyService.viewnumTop3(destId));
    }


}
