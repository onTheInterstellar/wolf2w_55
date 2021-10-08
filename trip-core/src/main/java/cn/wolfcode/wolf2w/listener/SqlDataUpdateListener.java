package cn.wolfcode.wolf2w.listener;

import cn.wolfcode.wolf2w.event.SqlDataUpdateEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SqlDataUpdateListener implements ApplicationListener<SqlDataUpdateEvent> {

    @Override
    public void onApplicationEvent(SqlDataUpdateEvent event) {

        System.err.println("检测更新了数据");

    }
}
