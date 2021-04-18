package com.guigu.mp.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;

    //添加时自动设置时间
    @TableField(fill = FieldFill.INSERT)
    private Date create_time;
    //更新时自动更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date update_time;

    //乐观锁版本号方法实现
    @Version
    @TableField(fill=FieldFill.INSERT)
    private Integer version;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;


}
