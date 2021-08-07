package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.Destination;
import cn.wolfcode.wolf2w.query.QueryObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IDestinationService extends IService<Destination> {

    /**
     * destination 分页查询
     * @param qo
     * @return
     */
    Page<Destination> QueryPage(QueryObject qo);

}
