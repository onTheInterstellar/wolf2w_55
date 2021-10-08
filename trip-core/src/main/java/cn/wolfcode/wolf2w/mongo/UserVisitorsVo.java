package cn.wolfcode.wolf2w.mongo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class UserVisitorsVo {

    private Long id;
    private String nickname;
    private String headImgUrl;
    private Date visitTime;
}
