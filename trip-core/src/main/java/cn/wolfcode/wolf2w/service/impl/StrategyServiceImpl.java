package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.*;
import cn.wolfcode.wolf2w.mapper.StrategyContentMapper;
import cn.wolfcode.wolf2w.mapper.StrategyMapper;
import cn.wolfcode.wolf2w.query.StrategyQuery;
import cn.wolfcode.wolf2w.service.IDestinationService;
import cn.wolfcode.wolf2w.service.IStrategyCatalogService;
import cn.wolfcode.wolf2w.service.IStrategyService;
import cn.wolfcode.wolf2w.service.IStrategyThemeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
* 用户服务接口实现
*/
@Service
@Transactional
public class StrategyServiceImpl extends ServiceImpl<StrategyMapper,Strategy> implements IStrategyService  {

    @Autowired
    private IStrategyCatalogService strategyCatalogService;

    @Autowired
    private IStrategyThemeService strategyThemeService;

    @Autowired
    private IDestinationService destinationService;

    @Autowired
    private StrategyContentMapper strategyContentMapper;

    @Override
    public List<Strategy> queryStrategiesById(Long id) {
        QueryWrapper<Strategy> wrapper = new QueryWrapper<>();
        wrapper.eq("dest_id", id);
        wrapper.orderByDesc("viewnum");
        wrapper.last(" limit 3");
        return super.list(wrapper);
    }

    @Override
    public IPage<Strategy> queryPage(StrategyQuery qo) {
        IPage<Strategy> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<Strategy> wrapper = Wrappers.<Strategy>query();

        wrapper.eq(qo.getDestId() != null, "dest_id", qo.getDestId())
                .eq(qo.getThemeId() != null, "theme_id", qo.getThemeId());

        if (qo.getType() != null && qo.getRefid() != null) {
            if (qo.getType() == StrategyCondition.TYPE_THEME)
                wrapper.eq(qo.getRefid() >0 && qo.getRefid() > 0,"theme_id", qo.getRefid());
            else
                wrapper.eq(qo.getRefid() >0 && qo.getRefid() > 0,"dest_id", qo.getRefid());
        }

        return super.page(page, wrapper);
    }

    @Override
    public boolean saveOrUpdate(Strategy strategy) {

        boolean flag = false;

        StrategyCatalog catalog = strategyCatalogService.getById(strategy.getCatalogId());
        strategy.setDestId(catalog.getDestId());
        strategy.setDestName(catalog.getDestName());
        strategy.setCatalogName(catalog.getName());
        strategy.setThemeName(strategyThemeService.getById(strategy.getThemeId()).getName());

        List<Destination> list = destinationService
                .queryToasts(destinationService.getById(catalog.getDestId()).getParentId());
        StrategyContent content = strategy.getContent();


        if (list.get(0).getId() == 1) {
            strategy.setIsabroad(Strategy.ABROAD_NO);
        } else {
            strategy.setIsabroad(Strategy.ABROAD_YES);
        }

        if (strategy.getId() == null) {

            strategy.setCreateTime(new Date());
            strategy.setViewnum(0);
            strategy.setReplynum(0);
            strategy.setFavornum(0);
            strategy.setSharenum(0);
            strategy.setThumbsupnum(0);

            flag = super.saveOrUpdate(strategy);
            content.setId(strategy.getId());
            strategyContentMapper.insert(content);

        } else {
            flag = super.updateById(strategy);
            content.setId(strategy.getId());
            strategyContentMapper.updateById(content);
        }

        return flag;
    }
}
