package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.domain.Strategy;
import cn.wolfcode.wolf2w.query.StrategyQuery;
import cn.wolfcode.wolf2w.service.IStrategyService;
import cn.wolfcode.wolf2w.util.JsonResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* 用户控制层
*/
@Controller
@RequestMapping("strategy")
public class StrategyController {

    @Autowired
    private IStrategyService strategyService;

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
    public String input(){
        return "strategy/input";
    }

}
