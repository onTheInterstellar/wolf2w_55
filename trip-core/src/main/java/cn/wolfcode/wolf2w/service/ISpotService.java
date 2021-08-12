package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.Spot;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ISpotService extends IService<Spot> {


    List<Spot> QuerySpot();

}
