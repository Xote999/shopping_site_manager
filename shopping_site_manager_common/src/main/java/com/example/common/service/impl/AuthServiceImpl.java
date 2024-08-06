package com.example.common.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.constants.HttpStatus;
import com.example.common.domain.dto.LoginDto;
import com.example.common.domain.vo.LoginUserVo;
import com.example.common.exception.ServiceException;
import com.example.common.service.IAuthService;
import com.example.common.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * Loginメソッド
     * @param loginDto
     * @return
     */
    @Override
    public String login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginDto.getAccount(), loginDto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authentication);

        //ユーザーメッセージの取得
        Object principal = authenticate.getPrincipal();
        if (principal instanceof LoginUserVo) {
            LoginUserVo loginUser = (LoginUserVo) principal;

            if (ObjectUtil.isNull(loginUser)) {
                throw new ServiceException(HttpStatus.UNAUTHORIZED, "認証失敗!");
            }

            //トークンの生成
            String token = jwtUtils.createToken(loginUser);
            log.info("token ====>{}", token);
            return token;
        } else {
            throw new ServiceException(HttpStatus.UNAUTHORIZED, "認証失敗!");
        }
    }
}
