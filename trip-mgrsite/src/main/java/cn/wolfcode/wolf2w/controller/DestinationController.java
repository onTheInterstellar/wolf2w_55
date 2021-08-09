package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.domain.Destination;
import cn.wolfcode.wolf2w.query.DestinationQuery;
import cn.wolfcode.wolf2w.service.IDestinationService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("destination")
public class DestinationController {

    @Autowired
    private IDestinationService destinationService;

    @RequestMapping("/list")
    private String list(ModelMap map, @ModelAttribute("qo") DestinationQuery qo) {

        Page<Destination> page = destinationService.QueryPage(qo);
        List<Destination> toasts = destinationService.queryToasts(qo.getParentId());
        map.put("page", page);
        map.put("toasts", toasts);

        return "/destination/list.html";

    }

}
