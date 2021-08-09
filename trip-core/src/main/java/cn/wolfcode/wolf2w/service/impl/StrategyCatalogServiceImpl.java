package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.StrategyCatalog;
import cn.wolfcode.wolf2w.mapper.StrategyCatalogMapper;
import cn.wolfcode.wolf2w.query.StrategyCatalogQuery;
import cn.wolfcode.wolf2w.service.IStrategyCatalogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* 用户服务接口实现
*/
@Service
@Transactional
public class StrategyCatalogServiceImpl extends ServiceImpl<StrategyCatalogMapper,StrategyCatalog> implements IStrategyCatalogService  {

    @Override
    public IPage<StrategyCatalog> queryPage(StrategyCatalogQuery qo) {
        IPage<StrategyCatalog> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<StrategyCatalog> wrapper = Wrappers.<StrategyCatalog>query();
        return super.page(page, wrapper);
    }
}
