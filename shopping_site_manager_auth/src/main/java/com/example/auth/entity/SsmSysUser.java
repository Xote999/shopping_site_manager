package com.example.auth.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("ssm_sys_user")
public class SsmSysUser implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String username;
    private String nickname;
    private String email;
    private String mobile;
    private Integer sex;
    private String avatar;
    private String password;
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
