package com.example.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.auth.entity.SsmMenu;
import com.example.auth.mapper.SsmMenuMapper;
import com.example.auth.service.ISsmMenuService;
import org.springframework.stereotype.Service;

@Service
public class SsmMenuServiceImpl extends ServiceImpl<SsmMenuMapper, SsmMenu> implements ISsmMenuService {
}
