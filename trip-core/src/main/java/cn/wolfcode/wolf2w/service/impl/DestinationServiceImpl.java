package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.Destination;
import cn.wolfcode.wolf2w.mapper.DestinationMapper;
import cn.wolfcode.wolf2w.query.DestinationQuery;
import cn.wolfcode.wolf2w.service.IDestinationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DestinationServiceImpl extends ServiceImpl<DestinationMapper, Destination> implements IDestinationService {

    @Override
    public Page<Destination> QueryPage(DestinationQuery qo) {
        Page<Destination> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq(qo.getParentId() != null,"parent_id", qo.getParentId());
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
}
