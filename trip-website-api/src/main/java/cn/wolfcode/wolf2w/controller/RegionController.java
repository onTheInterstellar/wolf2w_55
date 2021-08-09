package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.query.DestinationQuery;
import cn.wolfcode.wolf2w.service.IDestinationService;
import cn.wolfcode.wolf2w.service.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("regions")
public class RegionController {

    @Autowired
    private IDestinationService destinationService;

    @Autowired
    private IRegionService regionService;

    @GetMapping("/hotRegion")
    private Object list(ModelMap map, @ModelAttribute("qo") DestinationQuery qo) {

        //List<Destination> list = regionService.QueryHotRegion();

        return null;

    }

}
