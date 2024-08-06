package com.example.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.domain.entity.SsmRole;
import com.example.common.mapper.SsmRoleMapper;
import com.example.common.service.ISsmRoleService;
import org.springframework.stereotype.Service;

@Service
public class SsmRoleServiceImpl extends ServiceImpl<SsmRoleMapper, SsmRole> implements ISsmRoleService {
}
