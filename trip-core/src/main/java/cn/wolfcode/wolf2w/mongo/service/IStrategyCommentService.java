package cn.wolfcode.wolf2w.mongo.service;

import cn.wolfcode.wolf2w.mongo.domain.StrategyComment;
import cn.wolfcode.wolf2w.mongo.query.StrategyCommentQuery;
import org.springframework.data.domain.Page;

public interface IStrategyCommentService {

    /**
     * 保存攻略评论
     * @param comment
     */
    void saveComment(StrategyComment comment);

    /**
     * 根据StrategyId查询评论分页数据
     * @param qo
     * @return
     */
    Page<StrategyComment> queryPage(StrategyCommentQuery qo);
}
