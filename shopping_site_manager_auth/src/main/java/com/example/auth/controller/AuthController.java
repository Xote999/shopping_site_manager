package com.example.auth.controller;

import com.example.common.domain.dto.LoginDto;
import com.example.common.service.IAuthService;
import com.example.common.response.SsmSysUserResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * 認証
 */
@RestController
@RequestMapping("auth")
@Slf4j
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IAuthService authService;

    /**
     * ユーザーログイン
     */
    @PostMapping("sys")
    public SsmSysUserResult sysLogin(@RequestBody LoginDto loginDto) {

        log.info("loginDto ====>{}", loginDto);
        String token = authService.login(loginDto);
        return SsmSysUserResult.success().put("token", token);
    }

//    @GetMapping
//    public String test() {
//        String encode = passwordEncoder.encode("123456");
//        System.out.println("encode ====>"+encode);
//        return "";
//    }
}
