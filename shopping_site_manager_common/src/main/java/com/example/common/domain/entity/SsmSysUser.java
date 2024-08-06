package com.example.common.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@TableName("ssm_sys_user")
public class SsmSysUser implements Serializable {
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;
    private String userName;
    private String nickName;
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

    //権限リスト
    @TableField(exist = false)
    private List<SsmRole> roleList = new ArrayList<>();

    @TableField(exist = false)
    private List<String> perms = new ArrayList<>();
}
