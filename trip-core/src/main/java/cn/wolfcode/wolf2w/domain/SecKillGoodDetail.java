package cn.wolfcode.wolf2w.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter

public class SecKillGoodDetail {

    private Long id;
    private Long goodId;
    private BigDecimal seckillPrice;
    private int stockCount;
    private Date startDate;
    private Date endDate;
    private BigDecimal goodPrice;
    private String goodName;
    private String goodImg;
    private String goodDetail;
}
