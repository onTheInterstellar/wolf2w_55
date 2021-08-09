package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.Strategy;
import cn.wolfcode.wolf2w.query.StrategyQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


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
}
