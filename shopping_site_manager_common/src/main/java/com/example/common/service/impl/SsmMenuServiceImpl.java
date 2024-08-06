package com.example.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.domain.entity.SsmMenu;
import com.example.common.mapper.SsmMenuMapper;
import com.example.common.service.ISsmMenuService;
import org.springframework.stereotype.Service;

@Service
public class SsmMenuServiceImpl extends ServiceImpl<SsmMenuMapper, SsmMenu> implements ISsmMenuService {
}
