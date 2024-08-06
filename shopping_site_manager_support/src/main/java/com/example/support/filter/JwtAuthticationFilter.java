package com.example.support.filter;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.domain.vo.LoginUserVo;
import com.example.common.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 該当インターフェースはリクエスト前に一度実行され、リクエスト内のデータを取得します。その中で、トークンはリクエストヘッダーに含まれています
 * トークンを取得し、そのトークンを使ってRedisからユーザー情報を取得します
 */
@Component
@Slf4j
public class JwtAuthticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // ログインユーザーの取得
        LoginUserVo loginUserVo = (LoginUserVo) jwtUtils.getLoginUser(request);

        //　NULLチェック
        if(ObjectUtil.isNotNull(loginUserVo)){

            // 認証トークンを作成します（ユーザー情報と権限を含む）
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUserVo, null, loginUserVo.getAuthorities());

            // セキュリティコンテキストに認証情報を設定します
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        // フィルターチェーンを続行します
        filterChain.doFilter(request, response);
    }
}
