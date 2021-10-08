package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.domain.SecKillGood;
import cn.wolfcode.wolf2w.mapper.GoodMapper;
import cn.wolfcode.wolf2w.service.IGoodService;
import cn.wolfcode.wolf2w.service.ISecKillOrderService;
import cn.wolfcode.wolf2w.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* 横幅控制层
*/
@RestController
@RequestMapping("seckill/api")
public class GoodController {

    @Autowired
    private IGoodService goodService;

    @Autowired
    private ISecKillOrderService secKillOrderService;

    @Autowired
    private GoodMapper goodMapper;

    @GetMapping("seckillGood/query")
    public Object list(){

        List<SecKillGood> list = goodService.querySecKillGoods();
        return JsonResult.success(list);
    }

    @GetMapping("seckillGood/find")
    public Object detail(Long goodId){
        List<SecKillGood> secKillDetail = goodMapper.getSecKillDetail(goodId);
        return JsonResult.success(secKillDetail.get(0));
    }

    @PostMapping("seckillOrder/doSeckill")
    public Object doKill(Long userId, Long goodId){
        boolean result = secKillOrderService.doSecKill(userId, goodId);
        return JsonResult.success("正在抢购中,祝你好运");
    }
}
