package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.domain.Content;
import cn.wolfcode.wolf2w.domain.Spot;
import cn.wolfcode.wolf2w.service.IContentService;
import cn.wolfcode.wolf2w.service.ISpotService;
import cn.wolfcode.wolf2w.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("spots")
public class SpotController {

    @Autowired
    private ISpotService spotService;

    @Autowired
    private IContentService contentService;

    @GetMapping("/list")
    private Object spots() {

        List<Spot> list = spotService.list();

        return JsonResult.success(list);

    }

    @GetMapping("/detail")
    private Object detail(Long id) {

        Spot spot = spotService.getById(id);
        Content content = contentService.getById(id);
        spot.setContent(content);

        return JsonResult.success(spot);

    }
}
