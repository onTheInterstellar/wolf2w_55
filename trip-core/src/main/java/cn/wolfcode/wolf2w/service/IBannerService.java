package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.Banner;
import cn.wolfcode.wolf2w.query.BannerQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 横幅服务接口
 */
public interface IBannerService extends IService<Banner>{
    /**
    * 分页
    * @param qo
    * @return
    */
    IPage<Banner> queryPage(BannerQuery qo);

    /**
     * 通过类型查找banner
     * @param type
     * @return
     */
    List<Banner> getBannersByType(int type);

    /**
     * 根据类型查前五个travel banner
     * @param type
     * @return
     */
    List<Banner> getBannersType(int type);
}
