package cn.wolfcode.wolf2w.test;

import cn.wolfcode.wolf2w.domain.SecKillGood;
import cn.wolfcode.wolf2w.domain.SecKillOrder;
import cn.wolfcode.wolf2w.domain.TheTest;
import cn.wolfcode.wolf2w.mapper.GoodMapper;
import cn.wolfcode.wolf2w.mapper.SecKillOrderMapper;
import cn.wolfcode.wolf2w.mongo.service.IStrategyCommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class StrategyCommentServiceTest {

    @Autowired
    private IStrategyCommentService commentService;

    @Autowired
    private TheTest theTest;

    @Autowired
    private GoodMapper goodMapper;

    @Autowired
    private SecKillOrderMapper secKillOrderMapper;

    @Test
    public void testFind() {
        List<Long> list = Arrays.asList(1L, 2L);
        String s = list.toString();
        s = s.substring(1).substring(0,s.length()-2);
        System.out.println(s.length());
    }

    @Test
    public void testEvent() {

        List<SecKillGood> secKillGoods = goodMapper.getSecKillGoods();
        List<SecKillGood> secKillDetail = goodMapper.getSecKillDetail(1L);

        SecKillOrder goodInfo = secKillOrderMapper.getGoodInfo(1L);

        int count = secKillOrderMapper.getCount(1L);

        secKillOrderMapper.reduceSecKillOrder(1L);
        secKillOrderMapper.reduceOrder(1L);

    }

}
