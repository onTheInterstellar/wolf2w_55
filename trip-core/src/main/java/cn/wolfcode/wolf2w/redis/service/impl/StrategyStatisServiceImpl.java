package cn.wolfcode.wolf2w.redis.service.impl;

import cn.wolfcode.wolf2w.domain.Strategy;
import cn.wolfcode.wolf2w.redis.service.IStrategyStatisService;
import cn.wolfcode.wolf2w.redis.utils.RedisKeys;
import cn.wolfcode.wolf2w.redis.vo.StrategyStatisVo;
import cn.wolfcode.wolf2w.service.IStrategyService;
import cn.wolfcode.wolf2w.util.DateUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class StrategyStatisServiceImpl implements IStrategyStatisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private IStrategyService strategyService;

    @Override
    public void updateVo(Long id) {

        StrategyStatisVo statisVo = getStatisVo(id);
        statisVo.setViewnum(statisVo.getViewnum() + 1);
        setStatisVo(statisVo);

    }

    @Override
    public void replynumIncr(Long strategyId) {
        StrategyStatisVo statisVo = getStatisVo(strategyId);
        statisVo.setReplynum(statisVo.getReplynum() + 1);
        setStatisVo(statisVo);
    }

    @Override
    public boolean favor(Long sid, Long uid) {

        String favorKey = RedisKeys.USER_STRATEGY_FAVOR.join(uid.toString());
        List<Long> sidList = queryUserFavor(uid);

        StrategyStatisVo statisVo = this.getStatisVo(sid);
        if (sidList.contains(sid)) {
            statisVo.setFavornum(statisVo.getFavornum() - 1);
            sidList.remove(sid);
        } else {
            statisVo.setFavornum(statisVo.getFavornum() + 1);
            sidList.add(sid);
        }

        redisTemplate.opsForValue().set(favorKey, JSON.toJSONString(sidList));
        this.setStatisVo(statisVo);

        return sidList.contains(sid);
    }

    @Override
    public boolean thumbUp(Long sid, Long uid) {

        String thumbUpKey = RedisKeys.USER_STRATEGY_THUMB.join(uid.toString(), sid.toString());

        Date dateStart = new Date();
        Date dateEnd = DateUtil.getEndDate(dateStart);
        if (!redisTemplate.hasKey(thumbUpKey)) {
            StrategyStatisVo statisVo = this.getStatisVo(sid);
            statisVo.setThumbsupnum(statisVo.getThumbsupnum() + 1);
            this.setStatisVo(statisVo);
            redisTemplate.opsForValue().set(thumbUpKey, "今天已顶", DateUtil.getDateBetween(dateEnd,dateStart), TimeUnit.SECONDS);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Long> queryUserFavor(Long uid) {

        String favorKey = RedisKeys.USER_STRATEGY_FAVOR.join(uid.toString());
        List<Long> sidList = null;

        if (!redisTemplate.hasKey(favorKey)) {
            sidList = new ArrayList<>();
        } else {
            String sidListStr = redisTemplate.opsForValue().get(favorKey);
            sidList = JSON.parseArray(sidListStr, Long.class);
        }
        return sidList;
    }

    @Override
    public StrategyStatisVo getStatisVo(Long sid) {

        String voKey = RedisKeys.STRATEGY_STATIS_VO.join(sid.toString());

        StrategyStatisVo statisVo = null;
        if (!redisTemplate.hasKey(voKey)) {
            Strategy strategy = strategy = strategyService.getById(sid);
            statisVo = new StrategyStatisVo();
            BeanUtils.copyProperties(strategy, statisVo);
            statisVo.setStrategyId(sid);
        } else {
            String vo = redisTemplate.opsForValue().get(voKey);
            statisVo = JSON.parseObject(vo, StrategyStatisVo.class);
        }

        return statisVo;
    }

    @Override
    public void setStatisVo(StrategyStatisVo vo) {
        String voKey = RedisKeys.STRATEGY_STATIS_VO.join(vo.getStrategyId().toString());
        redisTemplate.opsForValue().set(voKey, JSON.toJSONString(vo));
    }

    @Override
    public void updateVoToTable() {
        Set<String> keys = redisTemplate.keys("*" + RedisKeys.STRATEGY_STATIS_VO.getPrefix() + "*");
        List<String> list = redisTemplate.opsForValue().multiGet(keys);
        list.stream().map(str -> JSON.parseObject(str, StrategyStatisVo.class))
                .map(vo -> {
                    Strategy strategy = new Strategy();
                            BeanUtils.copyProperties(vo, strategy);
                            strategy.setId(vo.getStrategyId());
                            return strategy;
                })
                .forEach(strategy -> strategyService.updateById(strategy));
    }

}
