package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.Strategy;
import cn.wolfcode.wolf2w.domain.StrategyCatalog;
import cn.wolfcode.wolf2w.mapper.StrategyCatalogMapper;
import cn.wolfcode.wolf2w.query.StrategyCatalogQuery;
import cn.wolfcode.wolf2w.service.IStrategyCatalogService;
import cn.wolfcode.wolf2w.service.IStrategyService;
import cn.wolfcode.wolf2w.vo.CatalogVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* 用户服务接口实现
*/
@Service
@Transactional
public class StrategyCatalogServiceImpl extends ServiceImpl<StrategyCatalogMapper,StrategyCatalog> implements IStrategyCatalogService  {

    @Autowired
    private IStrategyService strategyService;

    @Override
    public IPage<StrategyCatalog> queryPage(StrategyCatalogQuery qo) {
        IPage<StrategyCatalog> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<StrategyCatalog> wrapper = Wrappers.<StrategyCatalog>query();
        return super.page(page, wrapper);
    }

    @Override
    public List<CatalogVo> queryCatalogGroup() {

        /*
        * select dest_name, GROUP_CONCAT(id) ids, GROUP_CONCAT(name) names
          from strategy_catalog
          GROUP BY dest_name
        * */
        QueryWrapper<StrategyCatalog> wrapper = new QueryWrapper<>();
        wrapper.select("dest_name, GROUP_CONCAT(id) ids, GROUP_CONCAT(name) names");
        wrapper.groupBy("dest_name");

        List<Map<String, Object>> maps = super.listMaps(wrapper);

        List<CatalogVo> list = new ArrayList<>();
        for (Map<String, Object> map : maps) {

            CatalogVo vo = new CatalogVo();
            vo.setDestName(map.get("dest_name").toString());
            vo.setCatalogList(this.parseCatalogList(map.get("ids").toString(), map.get("names").toString()));
            list.add(vo);
        }

        return list;
    }

    private List<StrategyCatalog> parseCatalogList(String ids, String names) {

        List<StrategyCatalog> list = new ArrayList<>();
        String[] id = ids.split(",");
        String[] name = names.split(",");

        for (int i = 0; i < id.length; i++) {
            StrategyCatalog strategyCatalog = new StrategyCatalog();
            strategyCatalog.setId(Long.valueOf(id[i]));
            strategyCatalog.setName(name[i]);
            list.add(strategyCatalog);
        }

        return list;
    }


    @Override
    public List<StrategyCatalog> queryCatalogs(Long id) {

        QueryWrapper<StrategyCatalog> wrapper = new QueryWrapper<>();
        wrapper.eq("dest_id", id);
        List<StrategyCatalog> list = super.list(wrapper);

        QueryWrapper<Strategy> wrapper1 = new QueryWrapper<>();
        for (StrategyCatalog c : list) {
            wrapper1.clear();
            wrapper1.eq("catalog_id", c.getId());
            List<Strategy> list1 = strategyService.list(wrapper1);
            c.setStrategies(list1);
        }

        return list;
    }
}
