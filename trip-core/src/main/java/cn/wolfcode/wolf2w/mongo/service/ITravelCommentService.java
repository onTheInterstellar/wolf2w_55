package cn.wolfcode.wolf2w.mongo.service;

import cn.wolfcode.wolf2w.mongo.domain.TravelComment;

import java.util.List;

public interface ITravelCommentService {

    /**
     * 保存游记评论
     * @param comment
     */
    void saveComment(TravelComment comment);

    /**
     * 通过游记id查找游记评论
     * @param travelId
     * @return
     */
    List<TravelComment> findByTravelId(Long travelId);
}
