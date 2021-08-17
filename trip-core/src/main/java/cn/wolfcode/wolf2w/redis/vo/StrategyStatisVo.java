package cn.wolfcode.wolf2w.redis.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class StrategyStatisVo {

    private Long StrategyId;

    private int viewnum;
    private int replynum;
    private int favornum;
    private int sharenum;
    private int thumbsupnum;


}
