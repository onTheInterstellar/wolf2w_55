package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.domain.Strategy;
import cn.wolfcode.wolf2w.domain.StrategyTheme;
import cn.wolfcode.wolf2w.mapper.StrategyContentMapper;
import cn.wolfcode.wolf2w.query.StrategyQuery;
import cn.wolfcode.wolf2w.service.IStrategyCatalogService;
import cn.wolfcode.wolf2w.service.IStrategyService;
import cn.wolfcode.wolf2w.service.IStrategyThemeService;
import cn.wolfcode.wolf2w.util.JsonResult;
import cn.wolfcode.wolf2w.vo.CatalogVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
* 用户控制层
*/
@Controller
@RequestMapping("strategy")
public class StrategyController {

    @Autowired
    private IStrategyService strategyService;

    @Autowired
    private IStrategyThemeService strategyThemeService;

    @Autowired
    private IStrategyCatalogService strategyCatalogService;

    @Autowired
    private StrategyContentMapper strategyContentMapper;

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") StrategyQuery qo){
        IPage<Strategy> page = strategyService.queryPage(qo);
        model.addAttribute("page", page);
        return "strategy/list";
    }

    @RequestMapping("/get")
    @ResponseBody
    public Object get(Long id){
        return JsonResult.success(strategyService.getById(id));
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Strategy strategy){
        strategyService.saveOrUpdate(strategy);
        return JsonResult.success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long id){
        strategyService.removeById(id);
        return JsonResult.success();
    }

    @RequestMapping("/input")
    public String input(Long id, Model model){

        if (id != null) {
            Strategy strategy = strategyService.getById(id);
            strategy.setContent(strategyContentMapper.selectById(id));
            model.addAttribute("strategy", strategy);
        }

        List<StrategyTheme> themes = strategyThemeService.list();
        model.addAttribute("themes", themes);

        List<CatalogVo> catalogs = strategyCatalogService.queryCatalogGroup();
        model.addAttribute("catalogs", catalogs);





        return "strategy/input";

    }

}
