package com.yongsui.service;

import com.yongsui.dto.RoleDto;

public interface RoleService {

    // 通过角色id查询角色
    RoleDto findRoleByRoleId(Integer roleId);


}
