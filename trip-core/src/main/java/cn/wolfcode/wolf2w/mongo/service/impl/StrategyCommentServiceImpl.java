package cn.wolfcode.wolf2w.mongo.service.impl;

import cn.wolfcode.wolf2w.mongo.domain.StrategyComment;
import cn.wolfcode.wolf2w.mongo.query.StrategyCommentQuery;
import cn.wolfcode.wolf2w.mongo.repository.StrategyCommentRepository;
import cn.wolfcode.wolf2w.mongo.service.IStrategyCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StrategyCommentServiceImpl implements IStrategyCommentService {

    @Autowired
    private StrategyCommentRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveComment(StrategyComment comment) {

        //将id设置为null, 方式传入的是空字符串
        comment.setId(null);
        comment.setCreateTime(new Date());
        repository.save(comment);
    }

    @Override
    public Page<StrategyComment> queryPage(StrategyCommentQuery qo) {

        Query query = new Query();
        if (qo.getStrategyId() != null) {
            query.addCriteria(Criteria.where("strategyId").is(qo.getStrategyId()));
        }

        long totalCount = mongoTemplate.count(query, StrategyComment.class);
        if (totalCount != 0) {
            Pageable pageable = PageRequest.of(qo.getCurrentPage() - 1, qo.getPageSize());
            query.with(pageable);
            List<StrategyComment> data = mongoTemplate.find(query, StrategyComment.class);
            return new PageImpl<>(data, pageable, totalCount);
        } else {
            return Page.empty();
        }
    }
}
