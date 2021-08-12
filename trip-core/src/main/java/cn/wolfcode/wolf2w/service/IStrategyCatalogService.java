package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.StrategyCatalog;
import cn.wolfcode.wolf2w.query.StrategyCatalogQuery;
import cn.wolfcode.wolf2w.vo.CatalogVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


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

    /**
     * 分组下拉选数据
     * @return
     */
    List<CatalogVo> queryCatalogGroup();


    /**
     * 目的地概况查询
     * @param id
     * @return
     */
    List<StrategyCatalog> queryCatalogs(Long id);
}
