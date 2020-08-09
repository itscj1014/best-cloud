package com.van.auth.service;

import com.van.auth.manager.UserManager;
import com.van.auth.mapper.UserMapper;
import com.van.commons.entity.BestAuthUser;
import com.van.commons.entity.system.SystemUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 校验用户名和密码的类
 * @author van.shu
 * @create 2020/8/6 15:55
 */
@Service
public class BestUserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserManager userManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SystemUser systemUser = userManager.findByName(username);

        if (systemUser != null) {
            String userPermissions = userManager.findUserPermissions(username);
            boolean notLocked = false;
            if (StringUtils.equals(SystemUser.STATUS_VALID, systemUser.getStatus())) {
                notLocked = true;
            }
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils.NO_AUTHORITIES;
            if (StringUtils.isNoneBlank(userPermissions)) {
                grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(userPermissions);
            }

            BestAuthUser bestAuthUser = new BestAuthUser(systemUser.getUsername(), systemUser.getPassword(), true, true, true, notLocked, grantedAuthorities);

            BeanUtils.copyProperties(systemUser, bestAuthUser);
            return bestAuthUser;
        }else {
            throw new UsernameNotFoundException("用户名不存在");
        }

    }

}
