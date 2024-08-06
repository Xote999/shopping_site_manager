package com.example.support.config.security;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.domain.entity.SsmMenu;
import com.example.common.domain.entity.SsmRole;
import com.example.common.domain.entity.SsmSysUser;
import com.example.common.domain.vo.LoginUserVo;
import com.example.common.mapper.SsmMenuMapper;
import com.example.common.mapper.SsmSysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SysUserDetailsService implements UserDetailsService {

    @Autowired
    private SsmSysUserMapper ssmSysUserMapper;

    @Autowired
    private SsmMenuMapper ssmMenuMapper;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {

        log.info("loadUserByUserName ===>{}" + account);

        // アカウントタイプ
        int accountType = 0;

        // ユーザー情報を照会する
        SsmSysUser ssmSysUser = ssmSysUserMapper.selectUserByAccount(account,accountType);
        log.info("ssmSysUser ====>{}" + ssmSysUser);

        // 権限を取得する
        if(ObjectUtil.isNotNull(ssmSysUser)){
            List<SsmRole> roleList = ssmSysUser.getRoleList();
            List<Long> roleIds = roleList.stream().map(SsmRole::getRoleId).collect(Collectors.toList());
            log.info("roleIds ====>{}" + roleIds);

            // メニューを取得する
            List<SsmMenu> menuList = ssmMenuMapper.selectByRoleIds(roleIds);

            List<String> perms = menuList.stream().map(SsmMenu::getPerms).collect(Collectors.toList());
            log.info("perms ====>{}" + perms);
            ssmSysUser.setPerms(perms);
        }

        // Voインスタンスを作成する
        LoginUserVo loginUserVo = new LoginUserVo();
        loginUserVo.setSsmSysUser(ssmSysUser);
        loginUserVo.setUserId(ssmSysUser.getUserId());
        return loginUserVo;
    }
}
