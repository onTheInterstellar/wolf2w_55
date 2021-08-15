package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.Destination;
import cn.wolfcode.wolf2w.domain.Strategy;
import cn.wolfcode.wolf2w.domain.StrategyRecommend;
import cn.wolfcode.wolf2w.mapper.StrategyRecommendMapper;
import cn.wolfcode.wolf2w.service.IDestinationService;
import cn.wolfcode.wolf2w.service.IStrategyRecommendService;
import cn.wolfcode.wolf2w.service.IStrategyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* 攻略条件分时统计表服务接口实现
*/
@Service
@Transactional
public class StrategyRecommendServiceImpl extends ServiceImpl<StrategyRecommendMapper, StrategyRecommend> implements IStrategyRecommendService {

    @Autowired
    private IStrategyService strategyService;

    @Autowired
    private IDestinationService destinationService;

    @Override
    public void updateRecommend() {

        //select theme_id, theme_name, GROUP_CONCAT(dest_id) dest_ids, GROUP_CONCAT(dest_name) dest_names
        //from strategy
        //GROUP BY theme_id, theme_name

        QueryWrapper<Strategy> wrapper = new QueryWrapper<>();
        wrapper.select("theme_id, theme_name, GROUP_CONCAT(dest_id) dest_ids, GROUP_CONCAT(dest_name) dest_names")
                .groupBy("theme_id", "theme_name");

        List<Map<String, Object>> maps = strategyService.listMaps(wrapper);
        Date date = new Date();
        List<StrategyRecommend> list = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            StrategyRecommend strategyRecommend = new StrategyRecommend();
            strategyRecommend.setThemeId(Long.parseLong(map.get("theme_id").toString()));
            strategyRecommend.setThemeName(map.get("theme_name").toString());
            strategyRecommend.setDestIds(map.get("dest_ids").toString());
            strategyRecommend.setDestNames(map.get("dest_names").toString());
            strategyRecommend.setStatisTime(date);

            list.add(strategyRecommend);
        }

        super.saveBatch(list);

    }

    @Override
    public List<StrategyRecommend> queryStrategyRecommend() {

        //select *
        //from strategy_recommend
        //where statis_time = (select MAX(statis_time) from strategy_recommend)

        QueryWrapper<StrategyRecommend> wrapper = new QueryWrapper<>();
        wrapper.inSql("statis_time", "select MAX(statis_time) from strategy_recommend");

        List<StrategyRecommend> recommends = super.list(wrapper);

        for (StrategyRecommend recommend : recommends) {
            String[] ids = recommend.getDestIds().split(",");
            List<String> list1 = Arrays.asList(ids);
            List<Destination> list2 = destinationService.listByIds(list1);
            recommend.setDests(list2);
        }

        return recommends;
    }

    public List<Destination> parseDestination(String ids, String names) {

        String[] splitId = ids.split(",");
        String[] splitName = names.split(",");
        List<Destination> list = new ArrayList<>();
        for (int i = 0; i < splitId.length; i++) {
            Destination destination = new Destination(Long.parseLong(splitId[i]), splitName[i]);
            list.add(destination);
        }
        return list;
    }
}
















