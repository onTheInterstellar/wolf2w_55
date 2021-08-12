package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.domain.*;
import cn.wolfcode.wolf2w.mapper.StrategyContentMapper;
import cn.wolfcode.wolf2w.query.StrategyQuery;
import cn.wolfcode.wolf2w.service.IStrategyConditionService;
import cn.wolfcode.wolf2w.service.IStrategyRankService;
import cn.wolfcode.wolf2w.service.IStrategyService;
import cn.wolfcode.wolf2w.service.IStrategyThemeService;
import cn.wolfcode.wolf2w.util.JsonResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Autowired
    private IStrategyThemeService themeService;

    @Autowired
    private IStrategyRankService strategyRankService;

    @Autowired
    private IStrategyConditionService conditionService;

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

    @GetMapping("/themes")
    private Object themes(Long destId) {

        List<StrategyTheme> list = themeService.list();
        return JsonResult.success(list);
    }

    @GetMapping("/page")
    private Object page(StrategyQuery qo) {

        IPage<Strategy> page = strategyService.queryPage(qo);

        return JsonResult.success(page);
    }

    @GetMapping("/query")
    private Object query(StrategyQuery qo) {

        IPage<Strategy> page = strategyService.queryPage(qo);

        return JsonResult.success(page);
    }

    @GetMapping("/rank")
    private Object rank(int type) {

        List<StrategyRank> ranks = strategyRankService.queryRank(type);

        return JsonResult.success(ranks);
    }

    @GetMapping("/condition")
    private Object condition(int type) {

        List<StrategyCondition> conditions = conditionService.queryStrategyCondition(type);

        return JsonResult.success(conditions);
    }
}
