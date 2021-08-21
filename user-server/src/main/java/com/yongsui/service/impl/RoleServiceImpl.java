package com.yongsui.service.impl;

import com.yongsui.dto.RoleDto;
import com.yongsui.entity.Role;
import com.yongsui.mapper.RoleMapper;
import com.yongsui.service.RoleService;
import com.yongsui.utils.EmptyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 角色管理相关功能的实现类
 * @Author: tengmingfa
 * @Date: 2021年08月21日
 */
@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    RoleMapper roleMapper;

    @Override
    public RoleDto findRoleByRoleId(Integer roleId) {
        if(!EmptyUtils.isEmpty(roleId)){
            Role role = roleMapper.findRoleByRoleId(roleId);
            if(!EmptyUtils.isEmpty(role)){
                RoleDto roleDto = new RoleDto();
                BeanUtils.copyProperties(role,roleDto);
                return roleDto;
            }
        }
        logger.error("findRole方法参数异常");
        return null;
    }


}
