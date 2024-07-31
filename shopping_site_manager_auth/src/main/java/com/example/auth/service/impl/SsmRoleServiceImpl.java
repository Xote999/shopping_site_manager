package com.example.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.auth.entity.SsmRole;
import com.example.auth.mapper.SsmRoleMapper;
import com.example.auth.service.ISsmRoleService;
import org.springframework.stereotype.Service;

@Service
public class SsmRoleServiceImpl extends ServiceImpl<SsmRoleMapper, SsmRole> implements ISsmRoleService {
}
