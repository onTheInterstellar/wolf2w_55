package cn.wolfcode.wolf2w.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDomain {

    @TableId(type = IdType.AUTO)
    protected Long id;


}
