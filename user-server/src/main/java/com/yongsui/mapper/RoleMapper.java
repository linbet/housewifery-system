package com.yongsui.mapper;

import com.yongsui.entity.Role;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {

    // 通过角色id查询角色
    Role findRoleByRoleId(@Param("roleId") Integer roleId);


}
