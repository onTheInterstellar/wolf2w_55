package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.Banner;
import cn.wolfcode.wolf2w.mapper.BannerMapper;
import cn.wolfcode.wolf2w.query.BannerQuery;
import cn.wolfcode.wolf2w.service.IBannerService;
import cn.wolfcode.wolf2w.service.IStrategyService;
import cn.wolfcode.wolf2w.service.ITravelService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* 横幅服务接口实现
*/
@Service
@Transactional
public class BannerServiceImpl extends ServiceImpl<BannerMapper,Banner> implements IBannerService  {

    @Autowired
    private IStrategyService strategyService;

    @Autowired
    private ITravelService travelService;

    @Override
    public IPage<Banner> queryPage(BannerQuery qo) {
        IPage<Banner> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<Banner> wrapper = Wrappers.<Banner>query();
        return super.page(page, wrapper);
    }

    @Override
    public List<Banner> getBannersByType(int type) {
        return super.list(new QueryWrapper<Banner>()
                .eq("type", type));
    }

    @Override
    public List<Banner> getBannersType(int type) {
        QueryWrapper<Banner> wrapper = new QueryWrapper<Banner>()
                .eq("type", type)
                .orderByAsc("seq")
                .last("limit 5");
        List<Banner> list = super.list(wrapper);
        for (Banner banner : list) {
            if (type == Banner.TYPE_TRAVEL) {
                banner.setDestId(travelService.getById(banner.getRefid()).getDestId());
            }
        }
        return list;
    }
}
