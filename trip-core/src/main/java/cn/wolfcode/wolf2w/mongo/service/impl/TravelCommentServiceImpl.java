package cn.wolfcode.wolf2w.mongo.service.impl;

import cn.wolfcode.wolf2w.mongo.domain.TravelComment;
import cn.wolfcode.wolf2w.mongo.repository.TravelCommentRepository;
import cn.wolfcode.wolf2w.mongo.service.ITravelCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class TravelCommentServiceImpl implements ITravelCommentService {

    @Autowired
    private TravelCommentRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveComment(TravelComment comment) {

        //将id设置为null, 方式传入的是空字符串
        comment.setId(null);
        comment.setCreateTime(new Date());

        String refId;
        // 注意, 这里可能是一个空字符, 空字符也是 != null 的
        if (StringUtils.hasText((refId = comment.getRefComment().getId())))
            comment.setRefComment(repository.findById(refId).get());

        repository.save(comment);
    }

    @Override
    public List<TravelComment> findByTravelId(Long travelId) {
        return repository.findByTravelIdOrderByCreateTimeDesc(travelId);
    }
}
