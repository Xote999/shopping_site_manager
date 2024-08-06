package com.example.common.utils;

import com.example.common.constants.CacheConstants;
import com.example.common.domain.vo.LoginUserVo;
import com.example.common.utils.redis.RedisCacheUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * JWT ユーティリティクラス
 */
@Component
public class JwtUtils {

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    private String secret = "secretxxxxxx";

    /**
     * JWTトークンを生成する
     * redisに保存する
     */
    public String createToken(LoginUserVo loginUserVo) {

        String token = UUID.randomUUID().toString().replaceAll("-","");
        loginUserVo.setToken(token);


        Map<String, Object> claims = new HashMap<>();
        claims.put("token", token);

        // redisに保存する
        redisCacheUtil.setCacheObject(CacheConstants.LOGIN_USER_KEY + token, loginUserVo,30, TimeUnit.MINUTES);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    // トークンを解析する
    public Claims parseToken(String token){

        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * ログインユーザを取得する
     * redisから取得する
     * tokenをリフレッシュする　
     * @param request
     * @return
     */

    public Object getLoginUser(HttpServletRequest request) {

        // ヘッダからトークンを取得する
        String token = request.getHeader("ssmSys-Authorization");

        // トークンを解析する
        Claims claims = parseToken(token);
        System.out.println("claims====>" + claims);
        String parseToken = claims.get("token").toString();
        System.out.println("parseToken====>" + parseToken);

        // redisからログインユーザを取得する
        LoginUserVo loginUserVo = redisCacheUtil.getCacheObject(CacheConstants.LOGIN_USER_KEY + parseToken);

        return loginUserVo;
    }
}
