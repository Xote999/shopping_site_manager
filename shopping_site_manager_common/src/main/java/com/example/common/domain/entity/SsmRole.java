package com.example.common.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("ssm_role")
public class SsmRole implements Serializable {

    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;
    private String roleLabel;
    private String roleName;
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
