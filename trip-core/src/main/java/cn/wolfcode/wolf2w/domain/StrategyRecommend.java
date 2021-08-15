package cn.wolfcode.wolf2w.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 攻略条件统计表
 */
@Setter
@Getter
@TableName("strategy_recommend")
public class StrategyRecommend extends  BaseDomain{

    private Long themeId;
    private String themeName;
    private String destIds; //目的地字符串组合
    private String destNames; //目的地名字组合
    private Date statisTime; //归档统计时间
    @TableField(exist = false)
    private List<Destination> dests;

}
