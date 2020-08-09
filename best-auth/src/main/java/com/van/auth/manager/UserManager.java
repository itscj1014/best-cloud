package com.van.auth.manager;

import com.van.auth.mapper.MenuMapper;
import com.van.auth.mapper.UserMapper;
import com.van.commons.entity.system.Menu;
import com.van.commons.entity.system.SystemUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author van.shu
 * @Date 2020/8/8 22:05
 */
@Service
public class UserManager {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;


    public SystemUser findByName(String username) {

        return userMapper.findByName(username);
    }


    public String findUserPermissions(String username) {

        List<Menu> userPermissions = menuMapper.findUserPermissions(username);

        return userPermissions.stream().map(Menu::getPerms).collect(Collectors.joining(","));


    }
}
