package cn.wolfcode.wolf2w.redis.service;

import cn.wolfcode.wolf2w.redis.vo.StrategyStatisVo;

import java.util.List;

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

    /**
     * 将vo对象设置到redis中
     * @param vo
     * @return
     */
    void setStatisVo(StrategyStatisVo vo);

    /**
     * 增加评论数
     * @param strategyId
     */
    void replynumIncr(Long strategyId);

    /**
     * 用户点击收藏, 增加或减去收藏
     * @param sid
     * @param uid
     * @return
     */
    boolean favor(Long sid, Long uid);

    /**
     * 检查指定用户是否收藏指定文章
     * @param userId
     * @return
     */
    List<Long> queryUserFavor(Long userId);

}
