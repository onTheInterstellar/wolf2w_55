package cn.wolfcode.wolf2w.event;

import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

@Getter
public class SqlDataUpdateEvent extends ApplicationContextEvent {

    private Object data;
    public SqlDataUpdateEvent(ApplicationContext source, Object data) {
        super(source);
        this.data = data;
    }


}
