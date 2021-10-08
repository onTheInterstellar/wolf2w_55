package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.SecKillOrderV;
import cn.wolfcode.wolf2w.mapper.SecKillOrderVMapper;
import cn.wolfcode.wolf2w.service.ISecKillOrderVService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* 横幅服务接口实现
*/
@Service
@Transactional
public class SecKillOrderVServiceImpl extends ServiceImpl<SecKillOrderVMapper, SecKillOrderV> implements ISecKillOrderVService {


    @Override
    public boolean hasOrder(Long userId, Long goodId) {

        QueryWrapper<SecKillOrderV> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId).eq("good_id", goodId);
        SecKillOrderV one = super.getOne(wrapper);

        return one == null ? true:false;
    }
}
