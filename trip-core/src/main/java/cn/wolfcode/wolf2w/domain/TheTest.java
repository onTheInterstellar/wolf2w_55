package cn.wolfcode.wolf2w.domain;

import cn.wolfcode.wolf2w.event.SqlDataUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class TheTest {

    @Autowired
    private ApplicationContext applicationContext;


    public void startEvent(Object s) {
        System.out.println("触发事件");
        SqlDataUpdateEvent info = new SqlDataUpdateEvent(applicationContext, "info");
        applicationContext.publishEvent(info);

    }

}
