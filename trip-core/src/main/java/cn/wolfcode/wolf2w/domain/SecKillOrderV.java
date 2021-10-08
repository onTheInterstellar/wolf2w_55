package cn.wolfcode.wolf2w.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("t_seckill_order")
public class SecKillOrderV extends BaseDomain {

    private Long userId;
    private String orderNo;
    private Long goodId;

}
