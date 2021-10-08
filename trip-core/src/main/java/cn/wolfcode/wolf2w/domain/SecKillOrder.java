package cn.wolfcode.wolf2w.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@TableName("t_order_info")
public class SecKillOrder {

    @TableId(type = IdType.NONE)
    private String orderNo;

    private Long userId;
    private Long goodId;
    private String goodImg;
    private Long deliveryAddrId;
    private String goodName;
    private int goodCount;
    private BigDecimal goodPrice;
    private BigDecimal seckillPrice;
    private int status;
    private Date createDate;
    private Date payDate;


}
