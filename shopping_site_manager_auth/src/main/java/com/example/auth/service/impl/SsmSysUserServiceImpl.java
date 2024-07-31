package com.example.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.auth.entity.SsmSysUser;
import com.example.auth.mapper.SsmSysUserMapper;
import com.example.auth.service.ISsmSysUserService;
import org.springframework.stereotype.Service;

@Service
public class SsmSysUserServiceImpl extends ServiceImpl<SsmSysUserMapper, SsmSysUser> implements ISsmSysUserService {
}
