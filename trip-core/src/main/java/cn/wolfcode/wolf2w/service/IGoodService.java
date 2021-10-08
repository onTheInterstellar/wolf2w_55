package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.Good;
import cn.wolfcode.wolf2w.domain.SecKillGood;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 商品服务接口
 */
public interface IGoodService extends IService<Good>{

    /**
     * 查询秒杀商品详情
     * @return
     */
    List<SecKillGood> querySecKillGoods();

}
