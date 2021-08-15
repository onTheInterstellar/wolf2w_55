package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.StrategyRecommend;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 攻略条件分时统计表服务接口
 */
public interface IStrategyRecommendService extends IService<StrategyRecommend>{


    /**
     * 更新主题攻略分时统计表
     */
    void updateRecommend();


    /**
     * 主题推荐查询
     * @return
     */
    List<StrategyRecommend> queryStrategyRecommend();


}
