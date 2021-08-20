package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.domain.Destination;
import cn.wolfcode.wolf2w.domain.Strategy;
import cn.wolfcode.wolf2w.domain.Travel;
import cn.wolfcode.wolf2w.domain.UserInfo;
import cn.wolfcode.wolf2w.search.query.SearchQueryObject;
import cn.wolfcode.wolf2w.search.vo.SearchResultVO;
import cn.wolfcode.wolf2w.service.*;
import cn.wolfcode.wolf2w.util.JsonResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

/**
* 站内搜索
*/
@RestController
public class SearchController {

    @Autowired
    private IDestinationService destinationService;

    @Autowired
    private IStrategyService strategyService;
    
    @Autowired
    private ITravelService travelService;
    
    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IBannerService bannerService;

    @GetMapping("/q")
    public Object query(SearchQueryObject qo) throws UnsupportedEncodingException {

        String kw = URLDecoder.decode(qo.getKeyword(), "utf-8");
        qo.setKeyword(kw);
        switch (qo.getType()) {

            case SearchQueryObject.TYPE_DEST : return this.queryDestination(qo);
            case SearchQueryObject.TYPE_STRATEGY : return this.queryStrategy(qo);
            case SearchQueryObject.TYPE_TRAVEL : return this.queryTravel(qo);
            case SearchQueryObject.TYPE_USER : return this.queryUserInfo(qo);
            default : return this.queryAll(qo);
        }

    }

    private Object queryAll(SearchQueryObject qo) {
        return null;
    }

    private Object queryUserInfo(SearchQueryObject qo) {
        return null;
    }

    private Object queryTravel(SearchQueryObject qo) {
        return null;
    }

    private Object queryStrategy(SearchQueryObject qo) {
        return null;
    }

    private Object queryDestination(SearchQueryObject qo) {

        Destination destination = destinationService.getOne(new QueryWrapper<Destination>()
                .eq("name", qo.getKeyword()));

        SearchResultVO result = new SearchResultVO();  // total strategys travels users

        if (destination != null) {

            String name = destination.getName();
            List<Strategy> strategies = strategyService.list(new QueryWrapper<Strategy>().eq("dest_name", name));
            List<Travel> travels = travelService.list(new QueryWrapper<Travel>().eq("dest_name", name));
            List<UserInfo> users = userInfoService.list(new QueryWrapper<UserInfo>().eq("city", name));
            for (Travel travel : travels) {
                UserInfo user = userInfoService.getOne(new QueryWrapper<UserInfo>().eq("id", travel.getAuthorId()));
                travel.setAuthor(user);

            }
            result.setStrategys(strategies);
            result.setTravels(travels);
            result.setUsers(users);
            result.setTotal((long) strategies.size() + travels.size() + users.size());
        }



        HashMap<String, Object> map = new HashMap<>();

        map.put("dest", destination);
        map.put("result", result);
        map.put("qo", qo);

        return JsonResult.success(map);
    }

}
