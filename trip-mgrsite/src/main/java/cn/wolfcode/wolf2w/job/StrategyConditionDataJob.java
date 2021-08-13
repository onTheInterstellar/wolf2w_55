package cn.wolfcode.wolf2w.job;

import cn.wolfcode.wolf2w.domain.StrategyCondition;
import cn.wolfcode.wolf2w.service.IStrategyConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StrategyConditionDataJob {

    @Autowired
    private IStrategyConditionService conditionService;


    @Scheduled(cron = "* * 0/1 * * * ")
    public void doWork() {
        System.out.println("---------------攻略条件数据维护--start---------------");

        // 更新国外攻略条件
        conditionService.updateCondition(StrategyCondition.TYPE_ABROAD);
        // 更新国内攻略条件
        conditionService.updateCondition(StrategyCondition.TYPE_CHINA);
        // 更新热门攻略条件
        conditionService.updateCondition(StrategyCondition.TYPE_THEME);

        System.out.println("---------------攻略条件数据维护---end----------------");
    }

}
