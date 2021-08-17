package cn.wolfcode.wolf2w.query;


import lombok.Getter;
import lombok.Setter;

/**
* 游记查询参数封装对象
*/
@Setter
@Getter
public class TravelQuery extends  QueryObject{

    private Integer state;
    private Long destId;
    //    create_time&travelTimeType=-1&consumeType=-1&dayType=1&destId=355&currentPage=1
    private String orderBy;
    private int travelTimeType;
    private int consumeType;
    private int dayType;
}
