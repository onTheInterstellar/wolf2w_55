package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.SecKillOrder;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 商品服务接口
 */
public interface ISecKillOrderService extends IService<SecKillOrder>{


    /**
     * 秒杀下单业务
     * @param userId
     * @param goodId
     * @return
     */
    boolean doSecKill(Long userId, Long goodId);
}
