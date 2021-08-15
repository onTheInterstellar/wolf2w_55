package cn.wolfcode.wolf2w.job;

import cn.wolfcode.wolf2w.service.IStrategyRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StrategyRecommendDataJob {

    @Autowired
    private IStrategyRecommendService recommendService;


    @Scheduled(cron = "* * 0/1 * * * ")
    public void doWork() {
        System.out.println("---------------更新主题推荐分时统计表--start---------------");

        // 更新主题推荐分时统计表
        recommendService.updateRecommend();

        System.out.println("---------------更新主题推荐分时统计表---end----------------");
    }

}
