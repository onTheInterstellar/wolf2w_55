package cn.wolfcode.wolf2w.mongo.repository;

import cn.wolfcode.wolf2w.mongo.domain.TravelComment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TravelCommentRepository extends MongoRepository<TravelComment, String> {


    /**
     * jpa按照创建时间查travel评论
     * @param travelId
     * @return
     */
    //@Query(value = "select * from travel_comment limit 2")
    List<TravelComment> findByTravelIdOrderByCreateTimeDesc(Long travelId);
}
