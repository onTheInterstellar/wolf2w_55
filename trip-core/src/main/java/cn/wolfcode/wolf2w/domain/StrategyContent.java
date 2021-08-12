package cn.wolfcode.wolf2w.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 攻略内容
 */
@Setter
@Getter
@TableName("strategy_content")
public class StrategyContent implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String content;
}

