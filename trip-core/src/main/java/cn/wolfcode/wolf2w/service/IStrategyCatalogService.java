package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.StrategyCatalog;
import cn.wolfcode.wolf2w.query.StrategyCatalogQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 用户服务接口
 */
public interface IStrategyCatalogService extends IService<StrategyCatalog>{
    /**
    * 分页
    * @param qo
    * @return
    */
    IPage<StrategyCatalog> queryPage(StrategyCatalogQuery qo);
}
