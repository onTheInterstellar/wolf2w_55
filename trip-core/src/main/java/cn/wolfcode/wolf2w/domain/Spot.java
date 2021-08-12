package cn.wolfcode.wolf2w.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@TableName("spot")
@Setter
@Getter
public class Spot extends BaseDomain {

    private String name;
    private Long number;
    private String phone;
    private String img;
    private String ename;

    @TableField(exist = false)
    private Content content;

}
