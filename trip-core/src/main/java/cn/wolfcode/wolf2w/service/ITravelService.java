package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.Travel;
import cn.wolfcode.wolf2w.query.TravelQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


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

    /**
     * 游记审核
     * @param id
     * @param state
     */
    void audit(Long id, Integer state);

    /**
     * 根据id查询游记
     * @param id
     * @return
     */
    Travel detail(Long id);

    /**
     * 游记阅读量前三
     * @param destId
     * @return
     */
    List<Travel> viewnumTop3(Long destId);
}
