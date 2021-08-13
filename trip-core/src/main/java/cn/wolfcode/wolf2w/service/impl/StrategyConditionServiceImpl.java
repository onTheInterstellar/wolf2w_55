package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.Strategy;
import cn.wolfcode.wolf2w.domain.StrategyCondition;
import cn.wolfcode.wolf2w.mapper.StrategyConditionMapper;
import cn.wolfcode.wolf2w.query.StrategyConditionQuery;
import cn.wolfcode.wolf2w.service.IStrategyConditionService;
import cn.wolfcode.wolf2w.service.IStrategyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* 攻略条件分时统计表服务接口实现
*/
@Service
@Transactional
public class StrategyConditionServiceImpl extends ServiceImpl<StrategyConditionMapper,StrategyCondition> implements IStrategyConditionService  {

    @Autowired
    private IStrategyService strategyService;

    @Override
    public IPage<StrategyCondition> queryPage(StrategyConditionQuery qo) {
        IPage<StrategyCondition> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<StrategyCondition> wrapper = Wrappers.<StrategyCondition>query();
        return super.page(page, wrapper);
    }

    @Override
    public List<StrategyCondition> queryStrategyCondition(int type) {
        QueryWrapper<StrategyCondition> wrapper = new QueryWrapper<>();
        wrapper.eq("type", type)
                .inSql("statis_time", "select max(statis_time) from strategy_condition where type = " + type)
                .orderByDesc("count");
        return super.list(wrapper);
    }

    @Override
    public void updateCondition(Integer type) {

        //select dest_id refid, dest_name name, count(1) count
        //from strategy
        //where isabroad = 1
        //group by dest_id, dest_name
        //order by count desc;
        //
        //select theme_id refid, theme_name name, count(1) count
        //from strategy
        //group by theme_id, theme_name
        //order by count desc

        QueryWrapper<Strategy> wrapper = new QueryWrapper<>();

        if (type == StrategyCondition.TYPE_ABROAD)
            wrapper.select("dest_id refid, dest_name name, count(1) count")
                    .eq("isabroad", 1)
                    .groupBy("dest_id", "dest_name");

        if (type == StrategyCondition.TYPE_CHINA)
            wrapper.select("dest_id refid, dest_name name, count(1) count")
                    .eq("isabroad", 0)
                    .groupBy("dest_id", "dest_name");

        if (type == StrategyCondition.TYPE_THEME)
            wrapper.select("theme_id refid, theme_name name, count(1) count")
                    .groupBy("theme_id", "theme_name");

        wrapper.orderByDesc("count");

        List<Map<String, Object>> maps = strategyService.listMaps(wrapper);

        Date now = new Date();
        List<StrategyCondition> list = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            StrategyCondition strategyCondition = new StrategyCondition();
            strategyCondition.setRefid(Long.parseLong(map.get("refid").toString()));
            strategyCondition.setName(map.get("name").toString());
            strategyCondition.setCount(Integer.parseInt(map.get("count").toString()));
            strategyCondition.setType(type);
            strategyCondition.setStatisTime(now);

            list.add(strategyCondition);
        }

        super.saveBatch(list);


    }
}
















