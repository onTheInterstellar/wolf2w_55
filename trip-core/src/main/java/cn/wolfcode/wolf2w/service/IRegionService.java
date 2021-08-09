package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.Region;
import cn.wolfcode.wolf2w.query.QueryObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IRegionService extends IService<Region> {


    /**
     * region 分页查询
     * @param qo
     * @return
     */
    Page<Region> QueryPage(QueryObject qo);

    /**
     * 修改热门状态
     * @param hot
     * @param id
     */
    boolean changeHotValue(boolean hot, Long id);
}
