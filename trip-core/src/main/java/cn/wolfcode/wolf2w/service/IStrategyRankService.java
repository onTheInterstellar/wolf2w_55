package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.StrategyRank;
import cn.wolfcode.wolf2w.query.StrategyRankQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 攻略排行统计服务接口
 */
public interface IStrategyRankService extends IService<StrategyRank>{
    /**
    * 分页
    * @param qo
    * @return
    */
    IPage<StrategyRank> queryPage(StrategyRankQuery qo);


    /**
     * 查询指定类型攻略排行
     * @param type
     * @return
     */
    List<StrategyRank> queryRank(int type);


    /**
     * 攻略推荐排行管理
     * @param type
     */
    void updateRank(Integer type);

}
