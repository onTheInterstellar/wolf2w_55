package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.Destination;
import cn.wolfcode.wolf2w.query.DestinationQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IDestinationService extends IService<Destination> {

    /**
     * destination 分页查询
     * @param qo
     * @return
     */
    Page<Destination> QueryPage(DestinationQuery qo);

    /**
     * 查询路径上的目的地
     * @param parentId
     * @return
     */
    List<Destination> queryToasts(Long parentId);
}
