package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.Destination;
import cn.wolfcode.wolf2w.mapper.DestinationMapper;
import cn.wolfcode.wolf2w.query.QueryObject;
import cn.wolfcode.wolf2w.service.IDestinationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class DestinationServiceImpl extends ServiceImpl<DestinationMapper, Destination> implements IDestinationService {

    @Override
    public Page<Destination> QueryPage(QueryObject qo) {
        Page<Destination> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper wrapper = new QueryWrapper();

        wrapper.like(StringUtils.hasText(qo.getKeyword()), "name", qo.getKeyword());

        return super.page(page, wrapper);
    }
}
