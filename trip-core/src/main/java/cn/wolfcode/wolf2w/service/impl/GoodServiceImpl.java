package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.Good;
import cn.wolfcode.wolf2w.domain.SecKillGood;
import cn.wolfcode.wolf2w.mapper.GoodMapper;
import cn.wolfcode.wolf2w.service.IGoodService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* 横幅服务接口实现
*/
@Service
@Transactional
public class GoodServiceImpl extends ServiceImpl<GoodMapper,Good> implements IGoodService {

    @Autowired
    private GoodMapper goodMapper;

    @Override
    public List<SecKillGood> querySecKillGoods() {

        List<SecKillGood> secKillGoods = goodMapper.getSecKillGoods();
        return secKillGoods;
    }
}
