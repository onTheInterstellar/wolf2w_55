package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.domain.Destination;
import cn.wolfcode.wolf2w.domain.Strategy;
import cn.wolfcode.wolf2w.domain.Travel;
import cn.wolfcode.wolf2w.domain.UserInfo;
import cn.wolfcode.wolf2w.search.domain.DestinationEs;
import cn.wolfcode.wolf2w.search.domain.StrategyEs;
import cn.wolfcode.wolf2w.search.domain.TravelEs;
import cn.wolfcode.wolf2w.search.domain.UserInfoEs;
import cn.wolfcode.wolf2w.search.service.IDestinationEsService;
import cn.wolfcode.wolf2w.search.service.IStrategyEsService;
import cn.wolfcode.wolf2w.search.service.ITravelEsService;
import cn.wolfcode.wolf2w.search.service.IUserInfoEsService;
import cn.wolfcode.wolf2w.service.IDestinationService;
import cn.wolfcode.wolf2w.service.IStrategyService;
import cn.wolfcode.wolf2w.service.ITravelService;
import cn.wolfcode.wolf2w.service.IUserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ElasticSearchDataInitController<T,K,V,G> {
    @Autowired
    private IDestinationService destinationService;

    @Autowired
    private IStrategyService strategyService;

    @Autowired
    private ITravelService travelService;

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IDestinationEsService destinationEsService;

    @Autowired
    private IStrategyEsService strategyEsService;

    @Autowired
    private ITravelEsService travelEsService;

    @Autowired
    private IUserInfoEsService userInfoEsService;


    @RequestMapping("/dataInit")
    @ResponseBody
    public String dataInit() {

        this.destinationInit();
        this.strategyInit();
        this.travelInit();
        this.userInfoInit();

        return "done";
    }


    private void userInfoInit() {
        List<UserInfo> list = userInfoService.list();

        for (UserInfo userInfo : list) {
            UserInfoEs userInfoEs = new UserInfoEs();
            BeanUtils.copyProperties(userInfo, userInfoEs);
            userInfoEs.setId(userInfo.getId().toString());
            userInfoEsService.save(userInfoEs);
        }

    }

    private void travelInit() {
        List<Travel> list = travelService.list();

        for (Travel travel : list) {
            TravelEs travelEs = new TravelEs();
            BeanUtils.copyProperties(travel, travelEs);
            travelEs.setId(travel.getId().toString());
            travelEsService.save(travelEs);
        }
    }

    private void strategyInit() {
        List<Strategy> list = strategyService.list();

        for (Strategy strategy : list) {
            StrategyEs strategyEs = new StrategyEs();
            BeanUtils.copyProperties(strategy, strategyEs);
            strategyEs.setId(strategy.getId().toString());
            strategyEsService.save(strategyEs);
        }
    }

    private void destinationInit() {
        List<Destination> list = destinationService.list();

        for (Destination destination : list) {
            DestinationEs destinationEs = new DestinationEs();
            BeanUtils.copyProperties(destination, destinationEs);
            destinationEs.setId(destination.getId().toString());
            destinationEsService.save(destinationEs);
        }
    }




}
