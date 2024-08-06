package com.example.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.domain.entity.SsmSysUser;
import com.example.common.mapper.SsmSysUserMapper;
import com.example.common.service.ISsmSysUserService;
import org.springframework.stereotype.Service;

@Service
public class SsmSysUserServiceImpl extends ServiceImpl<SsmSysUserMapper, SsmSysUser> implements ISsmSysUserService {
}
