package com.yongsui.mapper;


import com.yongsui.entity.Permission;
import com.yongsui.entity.Role;
import com.yongsui.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Description: 用户mapper
 * @Author: tengmingfa
 * @Date: 2021年07月27日
 */
public interface UserMapper {

    // 通过邮箱查用户
    User findUserByEmail(@Param("email") String email);

    // 通过id查角色
    Role findRoleById(@Param("id") Long id);

    // 通过roleId查权限
    List<Permission> findPermissionListByRoleId(@Param("roleId") Long roleId);

}
