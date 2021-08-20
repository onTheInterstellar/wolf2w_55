package cn.wolfcode.wolf2w.search.vo;

import cn.wolfcode.wolf2w.domain.Strategy;
import cn.wolfcode.wolf2w.domain.Travel;
import cn.wolfcode.wolf2w.domain.UserInfo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Setter
@Getter
public class SearchResultVO implements Serializable{
    // total strategys travels users
    private Long total = 0L;
    private List<Strategy> strategys = Collections.emptyList();
    private List<Travel> travels = Collections.emptyList();
    private List<UserInfo> users = Collections.emptyList();

}
