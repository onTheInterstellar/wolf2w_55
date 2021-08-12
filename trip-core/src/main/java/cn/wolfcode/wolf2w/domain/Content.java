package cn.wolfcode.wolf2w.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@TableName("content")
@Getter
@Setter
public class Content {

    private Long id;
    private String content;

}
