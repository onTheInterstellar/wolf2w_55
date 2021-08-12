package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.domain.Strategy;
import cn.wolfcode.wolf2w.domain.StrategyContent;
import cn.wolfcode.wolf2w.mapper.StrategyContentMapper;
import cn.wolfcode.wolf2w.service.IStrategyService;
import cn.wolfcode.wolf2w.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 用户控制层
*/
@RestController
@RequestMapping("strategies")
public class StrategyController {

    @Autowired
    private StrategyContentMapper strategyContentMapper;

    @Autowired
    private IStrategyService strategyService;

    @GetMapping("/content")
    private Object content(Long id) {

        StrategyContent strategyContent = strategyContentMapper.selectById(id);
        return JsonResult.success(strategyContent);

    }

    @GetMapping("/detail")
    private Object detail(Long id) {

        Strategy strategy = strategyService.getById(id);
        strategy.setContent(strategyContentMapper.selectById(id));
        return JsonResult.success(strategy);

    }
}
