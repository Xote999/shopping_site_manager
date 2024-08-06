package com.example.common.domain.vo;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.domain.entity.SsmSysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class LoginUserVo implements UserDetails {

    //ユーザーメッセージ
    private SsmSysUser ssmSysUser;

    private String token;

    private Long userId;

    /**
     * ユーザーの権限
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> perms = ssmSysUser.getPerms();

        //権限をGrantedAuthorityに変換
        if(ObjectUtil.isNotEmpty(perms)){
            return perms.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public String getPassword() {
         return ssmSysUser.getPassword();
    }

    @Override
    public String getUsername() { return ssmSysUser.getUserName(); }

    @Override
    public boolean isAccountNonExpired() {
        return ssmSysUser.getStatus() == 0;
    }

    @Override
    public boolean isAccountNonLocked() {
        return ssmSysUser.getStatus() == 0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return ssmSysUser.getStatus() == 0;
    }

    @Override
    public boolean isEnabled() {
        return ssmSysUser.getStatus() == 0;
    }
}
