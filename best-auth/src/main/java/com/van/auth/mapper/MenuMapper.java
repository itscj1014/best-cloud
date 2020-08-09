package com.van.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.van.commons.entity.system.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> findUserPermissions(String username);
}