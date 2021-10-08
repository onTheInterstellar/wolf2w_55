package cn.wolfcode.wolf2w.mongo.domain;

import cn.wolfcode.wolf2w.mongo.UserVisitorsVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * 攻略评论
 */
@Setter
@Getter
@Document("user_visitor")
@ToString
public class UserVisitor implements Serializable {

    @Id
    private String id;
    private Long totalView;
    private Integer todayView;
    private List<UserVisitorsVo> visitors;

}