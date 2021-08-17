package cn.wolfcode.wolf2w.mongo.repository;

import cn.wolfcode.wolf2w.mongo.domain.TravelComment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TravelCommentRepository extends MongoRepository<TravelComment, String> {


    List<TravelComment> findByTravelId(Long travelId);
}
