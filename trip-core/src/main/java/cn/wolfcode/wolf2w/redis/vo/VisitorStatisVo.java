package cn.wolfcode.wolf2w.redis.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class VisitorStatisVo {

    private Long id;

    private int todayViewnum;
    private int viewnum;


}
