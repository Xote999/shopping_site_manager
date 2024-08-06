package com.example.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.common.domain.entity.SsmMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SsmMenuMapper extends BaseMapper<SsmMenu> {
    List<SsmMenu> selectByRoleIds(@Param("roleIds") List<Long> roleIds);
}
