package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.Region;
import cn.wolfcode.wolf2w.mapper.RegionMapper;
import cn.wolfcode.wolf2w.query.QueryObject;
import cn.wolfcode.wolf2w.service.IRegionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements IRegionService {

    @Override
    public Page<Region> QueryPage(QueryObject qo) {

        Page<Region> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.orderByAsc("seq");
        //wrapper.like("name", qo.getKeyword());
        return super.page(page, wrapper);
    }

    @Override
    public boolean changeHotValue(boolean hot, Long id) {
        UpdateWrapper wrapper = new UpdateWrapper();
        wrapper.set("ishot", hot);
        wrapper.eq("id", id);
        return super.update(wrapper);
    }

    @Override
    public List<Region> QueryHotRegion() {

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("ishot", Region.STATE_HOT);
        return super.list(wrapper);
    }
}
