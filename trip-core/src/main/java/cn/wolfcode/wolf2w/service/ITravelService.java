package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.Travel;
import cn.wolfcode.wolf2w.query.TravelQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 游记服务接口
 */
public interface ITravelService extends IService<Travel>{
    /**
    * 分页
    * @param qo
    * @return
    */
    IPage<Travel> queryPage(TravelQuery qo);
}
