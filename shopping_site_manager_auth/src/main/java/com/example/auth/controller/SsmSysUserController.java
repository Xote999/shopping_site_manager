package com.example.auth.controller;

import com.example.common.domain.entity.SsmSysUser;
import com.example.common.service.ISsmSysUserService;
import com.example.common.response.SsmSysUserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ssm/sysUser")
public class SsmSysUserController {

    @Autowired
    private ISsmSysUserService ssmSysUserService;

    @PostMapping
    public SsmSysUserResult addSysUser(@RequestBody SsmSysUser ssmSysUser) {
        boolean flag = ssmSysUserService.save(ssmSysUser);
        if(flag){
            return SsmSysUserResult.success();
        }
        return SsmSysUserResult.error();
    }

    @GetMapping
    public SsmSysUserResult searchList(){
        List<SsmSysUser> list = ssmSysUserService.list();
        list.forEach(System.out::println);
        return SsmSysUserResult.success(list);
    }
}
