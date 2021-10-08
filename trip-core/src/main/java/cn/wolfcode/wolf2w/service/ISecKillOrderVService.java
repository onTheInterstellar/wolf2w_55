package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.SecKillOrderV;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 商品服务接口
 */
public interface ISecKillOrderVService extends IService<SecKillOrderV>{


    /**
     * 检查该用户是否已经秒杀过
     * @param userId
     * @param goodId
     * @return
     */
    boolean hasOrder(Long userId, Long goodId);
}
