package com.example.common.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("ssm_menu")
public class SsmMenu implements Serializable {
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;
    private Long parentId;
    private String menuName;
    private String path;
    private String componentPath;
    private String perms;
    private String icon;
    private Integer menuType;
    private Integer sort;
    private Integer status;
    private String creator;
    private String updater;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    private String remark;

    @TableLogic(value = "0", delval = "1")
    private Integer deleted;
}
