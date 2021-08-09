package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.Destination;
import cn.wolfcode.wolf2w.domain.Region;
import cn.wolfcode.wolf2w.mapper.DestinationMapper;
import cn.wolfcode.wolf2w.query.DestinationQuery;
import cn.wolfcode.wolf2w.service.IDestinationService;
import cn.wolfcode.wolf2w.service.IRegionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class DestinationServiceImpl extends ServiceImpl<DestinationMapper, Destination> implements IDestinationService {

    @Autowired
    private IRegionService regionService;

    @Override
    public Page<Destination> QueryPage(DestinationQuery qo) {
        Page<Destination> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper wrapper = new QueryWrapper();

        HashMap<String, Object> map = new HashMap<>();
        map.put("parent_id", qo.getParentId());
        wrapper.allEq(map, true);

        wrapper.like(StringUtils.hasText(qo.getKeyword()), "name", qo.getKeyword());

        return super.page(page, wrapper);
    }


    @Override
    public List<Destination> queryToasts(Long parentId) {
        List<Destination> toasts = new ArrayList<>();
        while (parentId != null) {
            QueryWrapper<Destination> wrapper = new QueryWrapper<>();
            wrapper.eq("id", parentId);
            Destination one = super.getOne(wrapper);
            toasts.add(one);
            parentId = one.getParentId();
        }
        Collections.reverse(toasts);
        return toasts;
    }

    @Override
    public List<Destination> QueryHotRegion(Long regionId) {

        List<Destination> list = new ArrayList<>();
        QueryWrapper<Destination> wrapper = new QueryWrapper<>();
        if (regionId == -1) {
            wrapper.eq("parent_id", 1);
            list = super.list(wrapper);
        } else {
            Region region = regionService.getById(regionId);
            List<Long> ids = region.parseRefIds();
            list = super.listByIds(ids);
        }

        for (Destination destination : list) {
            wrapper.clear();
            wrapper.eq("parent_id", destination.getId());
            wrapper.last(" limit 10");
            destination.setChildren(super.list(wrapper));
        }

        return list;
    }
}
