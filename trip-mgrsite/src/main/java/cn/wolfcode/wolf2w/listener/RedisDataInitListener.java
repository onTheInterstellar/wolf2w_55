package cn.wolfcode.wolf2w.listener;

import cn.wolfcode.wolf2w.redis.service.IStrategyStatisService;
import cn.wolfcode.wolf2w.redis.utils.RedisKeys;
import cn.wolfcode.wolf2w.redis.vo.StrategyStatisVo;
import cn.wolfcode.wolf2w.service.IStrategyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisDataInitListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private IStrategyService strategyService;

    @Autowired
    private IStrategyStatisService statisService;

    @Autowired
    private StringRedisTemplate template;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("-----------监听到spring容器启动-初始化vo缓存数据----------");

        strategyService.list().stream().map(strategy -> {
            StrategyStatisVo statisVo = new StrategyStatisVo();
            BeanUtils.copyProperties(strategy, statisVo);
            statisVo.setStrategyId(strategy.getId());
            return statisVo;
        }).filter(vo -> !template.hasKey(
                RedisKeys.STRATEGY_STATIS_VO.join(vo.getStrategyId().toString())))
          .forEach(vo -> statisService.setStatisVo(vo));


        System.out.println("------------初始化vo缓存数据成功----------");
    }
}
