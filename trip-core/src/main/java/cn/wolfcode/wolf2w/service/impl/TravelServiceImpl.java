package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.Travel;
import cn.wolfcode.wolf2w.mapper.TravelMapper;
import cn.wolfcode.wolf2w.query.TravelQuery;
import cn.wolfcode.wolf2w.service.ITravelService;
import cn.wolfcode.wolf2w.service.IUserInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* 游记服务接口实现
*/
@Service
@Transactional
public class TravelServiceImpl extends ServiceImpl<TravelMapper,Travel> implements ITravelService  {

    @Autowired
    private IUserInfoService userInfoService;

    @Override
    public IPage<Travel> queryPage(TravelQuery qo) {
        IPage<Travel> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<Travel> wrapper = Wrappers.<Travel>query();

        IPage<Travel> pages = super.page(page, wrapper);
        for (Travel record : pages.getRecords()) {
            record.setAuthor(userInfoService.getById(record.getAuthorId()));
        }

        return pages;
    }
}
