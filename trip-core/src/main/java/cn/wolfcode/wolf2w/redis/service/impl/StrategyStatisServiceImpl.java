package cn.wolfcode.wolf2w.redis.service.impl;

import cn.wolfcode.wolf2w.domain.Strategy;
import cn.wolfcode.wolf2w.redis.service.IStrategyStatisService;
import cn.wolfcode.wolf2w.redis.utils.RedisKeys;
import cn.wolfcode.wolf2w.redis.vo.StrategyStatisVo;
import cn.wolfcode.wolf2w.service.IStrategyService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StrategyStatisServiceImpl implements IStrategyStatisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private IStrategyService strategyService;

    @Override
    public void updateVo(Long id) {

        String voKey = RedisKeys.STRATEGY_STATIS_VO.join(id.toString());

        Strategy strategy = null;
        StrategyStatisVo statisVo = null;
        if (!redisTemplate.hasKey(voKey)) {
            strategy = strategyService.getById(id);
            statisVo = new StrategyStatisVo();
            BeanUtils.copyProperties(strategy, statisVo);
            statisVo.setStrategyId(id);
        } else {
            String vo = redisTemplate.opsForValue().get(voKey);
            statisVo = JSON.parseObject(vo, StrategyStatisVo.class);
            statisVo.setViewnum(statisVo.getViewnum() + 1);
        }

        redisTemplate.opsForValue().set(voKey, JSON.toJSONString(statisVo));

    }

    @Override
    public StrategyStatisVo getStatisVo(Long sid) {

        String voKey = RedisKeys.STRATEGY_STATIS_VO.join(sid.toString());
        return JSON.parseObject(redisTemplate.opsForValue().get(voKey), StrategyStatisVo.class);
    }

    @Override
    public void updateVoToTable() {
        Set<String> keys = redisTemplate.keys("*" + RedisKeys.STRATEGY_STATIS_VO.getPrefix() + "*");
        List<String> list = redisTemplate.opsForValue().multiGet(keys);
        list.stream().map(str -> JSON.parseObject(str, StrategyStatisVo.class)).map(vo -> {Strategy strategy = new Strategy();BeanUtils.copyProperties(vo, strategy);strategy.setId(vo.getStrategyId());return strategy; }).forEach(strategy -> strategyService.updateById(strategy));
    }
}
