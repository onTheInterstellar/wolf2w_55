package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.SecKillOrder;
import cn.wolfcode.wolf2w.domain.SecKillOrderV;
import cn.wolfcode.wolf2w.mapper.SecKillOrderMapper;
import cn.wolfcode.wolf2w.mapper.SecKillOrderVMapper;
import cn.wolfcode.wolf2w.service.ISecKillOrderService;
import cn.wolfcode.wolf2w.service.ISecKillOrderVService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
* 横幅服务接口实现
*/
@Service
@Transactional
public class SecKillOrderServiceImpl extends ServiceImpl<SecKillOrderMapper, SecKillOrder> implements ISecKillOrderService {

    @Autowired
    private SecKillOrderMapper secKillOrderMapper;

    @Autowired
    private ISecKillOrderVService secKillOrderVService;

    @Autowired
    private SecKillOrderVMapper secKillOrderVMapper;


    @Override
    public boolean doSecKill(Long userId, Long goodId) {


        if (secKillOrderMapper.getCount(goodId) > 0 && secKillOrderVService.hasOrder(userId, goodId)) {
            SecKillOrder order= secKillOrderMapper.getGoodInfo(goodId);
            order.setOrderNo(UUID.randomUUID().toString());
            order.setUserId(userId);
            order.setDeliveryAddrId(1L);
            order.setGoodCount(1);
            order.setStatus(1);
            order.setCreateDate(new Date());
            order.setPayDate(new Date());

            SecKillOrderV orderV = new SecKillOrderV();
            orderV.setGoodId(goodId);
            orderV.setOrderNo(order.getOrderNo());
            orderV.setUserId(userId);
            secKillOrderVMapper.insert(orderV);

            secKillOrderMapper.insert(order);
            secKillOrderMapper.reduceOrder(goodId);
            secKillOrderMapper.reduceSecKillOrder(goodId);
            return true;
        }

        return false;

    }
}
