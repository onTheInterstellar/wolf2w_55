package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.Travel;
import cn.wolfcode.wolf2w.exception.LogicException;
import cn.wolfcode.wolf2w.mapper.TravelContentMapper;
import cn.wolfcode.wolf2w.mapper.TravelMapper;
import cn.wolfcode.wolf2w.query.TravelCondition;
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

import java.util.Date;
import java.util.List;

/**
* 游记服务接口实现
*/
@Service
@Transactional
public class TravelServiceImpl extends ServiceImpl<TravelMapper,Travel> implements ITravelService  {

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private TravelContentMapper travelContentMapper;

    @Override
    public IPage<Travel> queryPage(TravelQuery qo) {
        IPage<Travel> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<Travel> wrapper = Wrappers.<Travel>query();
        wrapper.eq(qo.getState() != null, "state", qo.getState())
                .eq(qo.getDestId() != null, "dest_id", qo.getDestId())
                .orderByDesc(qo.getOrderBy() != null, qo.getOrderBy());

        TravelCondition condition;
        if ((condition = TravelCondition.DAY_MAP.get(qo.getDayType())) != null)
            wrapper.ge("day", condition.getMin()).le("day", condition.getMax());
        if ((condition = TravelCondition.CONSUME_MAP.get(qo.getConsumeType())) != null)
            wrapper.ge("avg_consume", condition.getMin()).le("avg_consume", condition.getMax());
        if ((condition = TravelCondition.TRAVEL_TIME_MAP.get(qo.getTravelTimeType())) != null)
            wrapper.ge("month(travel_time)", condition.getMin()).le("month(travel_time)", condition.getMax());


        IPage<Travel> pages = super.page(page, wrapper);
        for (Travel record : pages.getRecords()) {
            record.setAuthor(userInfoService.getById(record.getAuthorId()));
        }

        return pages;
    }


    @Override
    public void audit(Long id, Integer state) {

        Travel oneTravel = super.getById(id);
        // 判断游记是否存在
        // 游记审核状态是否是审核中
        // 审核通过: 修改发布时间, 更新时间, 转态
        if (oneTravel == null || oneTravel.getState() != Travel.STATE_WAITING)
            throw new LogicException("参数异常");
        if (state == Travel.STATE_RELEASE) {
            Date date = new Date();
            oneTravel.setReleaseTime(date);
            oneTravel.setLastUpdateTime(date);
            oneTravel.setState(state);
        } else if (state == Travel.STATE_REJECT) {
            oneTravel.setReleaseTime(null);
            oneTravel.setState(state);
        } else {
            throw new LogicException("参数异常");
        }

        super.updateById(oneTravel);
    }

    @Override
    public Travel detail(Long id) {
        Travel travel = super.getById(id);
        travel.setContent(travelContentMapper.selectById(travel.getId()));
        travel.setAuthor(userInfoService.getById(travel.getAuthorId()));
        return travel;
    }

    @Override
    public List<Travel> viewnumTop3(Long destId) {
        QueryWrapper<Travel> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("viewnum")
                .last("limit 3");
        return super.list(wrapper);
    }
}
