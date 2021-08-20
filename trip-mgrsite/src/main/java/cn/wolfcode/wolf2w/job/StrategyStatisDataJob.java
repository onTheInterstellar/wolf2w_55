package cn.wolfcode.wolf2w.job;

import cn.wolfcode.wolf2w.redis.service.IStrategyStatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

//@Component
public class StrategyStatisDataJob {

    @Autowired
    private IStrategyStatisService statisService;

    @Scheduled(cron = "0/5 * * * * *")
    public void doWork() {

        System.out.println("---------------攻略统计数据维护--start---------------");

        statisService.updateVoToTable();

        System.out.println("---------------攻略统计数据维护---end---------------");

    }

}
