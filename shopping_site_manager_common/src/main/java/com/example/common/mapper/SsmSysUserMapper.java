package com.example.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.common.domain.entity.SsmSysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SsmSysUserMapper extends BaseMapper<SsmSysUser> {
    SsmSysUser selectUserByAccount(@Param("account") String account, @Param("accountType") int accountType);
}
