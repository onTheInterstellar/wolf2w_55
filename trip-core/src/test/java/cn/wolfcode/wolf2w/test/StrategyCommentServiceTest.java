package cn.wolfcode.wolf2w.test;

import cn.wolfcode.wolf2w.mongo.service.IStrategyCommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StrategyCommentServiceTest {

    @Autowired
    private IStrategyCommentService commentService;

    @Test
    public void testFind() {

    }

}
