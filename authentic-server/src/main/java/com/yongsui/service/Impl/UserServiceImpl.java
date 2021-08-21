package com.yongsui.service.Impl;

import com.yongsui.dto.PermissionDto;
import com.yongsui.dto.RoleDto;
import com.yongsui.dto.UserDto;
import com.yongsui.entity.Permission;
import com.yongsui.entity.Role;
import com.yongsui.entity.User;
import com.yongsui.mapper.UserMapper;
import com.yongsui.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 用户service实现
 * @Author: tengmingfa
 * @Date: 2021年07月27日
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    // 为security框架提供信息源
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDto userDto = new UserDto();
        if(email != null && email != ""){
            User user = userMapper.findUserByEmail(email);
            if(user != null){
                BeanUtils.copyProperties(user,userDto);
                Long roleId = user.getRoleId();
                RoleDto roleDto = this.findRoleById(roleId);
                userDto.setRole(roleDto);
                return userDto;
            }
        }
        return userDto;
    }

    // 查询用户时的辅助方法，查角色信息
    public RoleDto findRoleById(Long id) {
        if(id != null){
            RoleDto roleDto = new RoleDto();
            Role role = userMapper.findRoleById(id);
            if(role != null){
                BeanUtils.copyProperties(role,roleDto);
                Long roleId = role.getId();
                List<Permission> permissionList =  userMapper.findPermissionListByRoleId(roleId);
                if(permissionList != null && permissionList.size() != 0){
                    List<PermissionDto> permissionDtoList = new LinkedList<>();
                    for(Permission permission : permissionList){
                        PermissionDto permissionDto = new PermissionDto();
                        BeanUtils.copyProperties(permission,permissionDto);
                        permissionDtoList.add(permissionDto);
                    }
                    roleDto.setPermissionDtoList(permissionDtoList);
                }else{
                    roleDto.setPermissionDtoList(null);
                }
                return roleDto;
            }
        }
        return null;
    }

}
