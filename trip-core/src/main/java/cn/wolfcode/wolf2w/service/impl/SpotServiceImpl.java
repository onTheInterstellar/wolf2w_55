package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.Spot;
import cn.wolfcode.wolf2w.mapper.SpotMapper;
import cn.wolfcode.wolf2w.service.IContentService;
import cn.wolfcode.wolf2w.service.ISpotService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpotServiceImpl extends ServiceImpl<SpotMapper, Spot> implements ISpotService {

    @Autowired
    private IContentService contentService;


    @Override
    public List<Spot> QuerySpot() {




        return null;
    }
}
