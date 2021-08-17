package cn.wolfcode.wolf2w.redis.service;

import cn.wolfcode.wolf2w.redis.vo.StrategyStatisVo;

/*
*  攻略数据据统计业务接口
* */
public interface IStrategyStatisService {


    /**
     * 更新攻略统计数据
     * @param id
     */
    void updateVo(Long id);

    /**
     * 根据攻略id查询statisVo 数据
     * @param sid
     * @return
     */
    StrategyStatisVo getStatisVo(Long sid);

    /**
     * 将redis数据更新到MySQL数据库中
     */
    void updateVoToTable();

}
