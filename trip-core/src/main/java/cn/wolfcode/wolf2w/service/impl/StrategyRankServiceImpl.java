package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.Strategy;
import cn.wolfcode.wolf2w.domain.StrategyRank;
import cn.wolfcode.wolf2w.mapper.StrategyRankMapper;
import cn.wolfcode.wolf2w.query.StrategyRankQuery;
import cn.wolfcode.wolf2w.service.IStrategyRankService;
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
* 攻略排行统计服务接口实现
*/
@Service
@Transactional
public class StrategyRankServiceImpl extends ServiceImpl<StrategyRankMapper,StrategyRank> implements IStrategyRankService  {

    @Autowired
    private IStrategyService strategyService;

    @Override
    public IPage<StrategyRank> queryPage(StrategyRankQuery qo) {
        IPage<StrategyRank> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<StrategyRank> wrapper = Wrappers.<StrategyRank>query();
        return super.page(page, wrapper);
    }

    @Override
    public List<StrategyRank> queryRank(int type) {

       /* select * from strategy_rank
        where type = 3   and statis_time in (select max(statis_time) from strategy_rank where type = 3 )
        order by statisnum desc
        limit 10*/

        QueryWrapper<StrategyRank> wrapper = new QueryWrapper<>();
        wrapper.eq("type", type)
                .orderByDesc("statisnum")
                .inSql("statis_time", "select max(statis_time) from strategy_rank where type = " + type)
                .last("limit 10");
        return super.list(wrapper);
    }


    @Override
    public void updateRank(Integer type) {
        //select dest_id, dest_name, id strategy_Id, title strategy_title, viewnum + replynum count
        //from strategy
        //where isabroad = 0
        //order by count desc
        //limit 10

        QueryWrapper<Strategy> wrapper = new QueryWrapper<>();

        if (type == StrategyRank.TYPE_HOT) {

            wrapper.select("dest_id, dest_name, id strategy_Id, title strategy_title, viewnum + replynum count");

        } else if (type == StrategyRank.TYPE_CHINA) {

            //select dest_id, dest_name, id strategy_Id, title strategy_title, favornum + thumbsupnum count
            wrapper.select("dest_id, dest_name, id strategy_Id, title strategy_title, favornum + thumbsupnum count")
                    .eq("isabroad", 0);

        } else {

            wrapper.select("dest_id, dest_name, id strategy_Id, title strategy_title, favornum + thumbsupnum count")
                    .eq("isabroad", 1);

        }

        wrapper.orderByDesc("count")
                .last("limit 10");

        List<Map<String, Object>> maps = strategyService.listMaps(wrapper);

        Date now = new Date();

        List<StrategyRank> list = new ArrayList<>();
        for (Map<String, Object> map : maps) {

            StrategyRank strategyRank = new StrategyRank();
            strategyRank.setDestId(Long.parseLong(map.get("dest_id").toString()));
            strategyRank.setDestName(map.get("dest_name").toString());
            strategyRank.setStrategyId(Long.parseLong(map.get("strategy_Id").toString()));
            strategyRank.setStrategyTitle(map.get("strategy_title").toString());
            strategyRank.setStatisnum(Long.parseLong(map.get("count").toString()));
            strategyRank.setType(type);
            strategyRank.setStatisTime(now);

            list.add(strategyRank);

        }

        super.saveBatch(list);


    }
}
