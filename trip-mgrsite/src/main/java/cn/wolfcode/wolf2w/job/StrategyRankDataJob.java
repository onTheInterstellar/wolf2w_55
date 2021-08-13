package cn.wolfcode.wolf2w.job;

import cn.wolfcode.wolf2w.domain.StrategyRank;
import cn.wolfcode.wolf2w.service.IStrategyRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StrategyRankDataJob {

    @Autowired
    private IStrategyRankService rankService;


    @Scheduled(cron = "* * 0/1 * * * ")
    public void doWork() {
        System.out.println("---------------攻略推荐数据维护--start---------------");

        // 更新国外攻略推荐榜
        rankService.updateRank(StrategyRank.TYPE_ABROAD);
        // 更新国内攻略推荐榜
        rankService.updateRank(StrategyRank.TYPE_CHINA);
        // 更新热门攻略推荐榜
        rankService.updateRank(StrategyRank.TYPE_HOT);


        System.out.println("---------------攻略推荐数据维护---end----------------");
    }

}
