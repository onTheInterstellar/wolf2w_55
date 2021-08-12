package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.domain.StrategyCondition;
import cn.wolfcode.wolf2w.query.StrategyConditionQuery;
import cn.wolfcode.wolf2w.service.IStrategyConditionService;
import cn.wolfcode.wolf2w.util.JsonResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 攻略条件分时统计表控制层
*/
@RestController
@RequestMapping("strategyCondition")
public class StrategyConditionController {

    @Autowired
    private IStrategyConditionService strategyConditionService;

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") StrategyConditionQuery qo){
        IPage<StrategyCondition> page = strategyConditionService.queryPage(qo);
        model.addAttribute("page", page);
        return "strategyCondition/list";
    }

    @RequestMapping("/get")
    public Object get(Long id){
        return JsonResult.success(strategyConditionService.getById(id));
    }

    @RequestMapping("/saveOrUpdate")
    public Object saveOrUpdate(StrategyCondition strategyCondition){
        strategyConditionService.saveOrUpdate(strategyCondition);
        return JsonResult.success();
    }

    @RequestMapping("/delete")
    public Object delete(Long id){
        strategyConditionService.removeById(id);
        return JsonResult.success();
    }
}
