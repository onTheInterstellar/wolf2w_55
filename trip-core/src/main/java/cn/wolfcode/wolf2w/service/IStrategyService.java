package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.Strategy;
import cn.wolfcode.wolf2w.query.StrategyQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 用户服务接口
 */
public interface IStrategyService extends IService<Strategy>{
    /**
    * 分页
    * @param qo
    * @return
    */
    IPage<Strategy> queryPage(StrategyQuery qo);


    /**
     * 重写saveOrUpdate
     * @param strategy
     * @return
     */
    boolean saveOrUpdate(Strategy strategy);

    /**
     * 查找最高浏览量前三
     * @param id
     * @return
     */
    List<Strategy> queryStrategiesById(Long id);
}
