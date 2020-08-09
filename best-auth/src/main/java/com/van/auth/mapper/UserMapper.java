package com.van.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.van.commons.entity.system.SystemUser;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper extends BaseMapper<SystemUser> {
    SystemUser findByName(String username);
}