package com.yongsui.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 角色数据传输类
 * @Author: tengmingfa
 * @Date: 2021年07月28日
 */
public class RoleDto implements Serializable {

    private static final long serialVersionUID = 4531843405870599470L;

    private Long id;

    private String roleName;

    private String roleDsc;

    private Integer status;

    private List<PermissionDto> permissionDtoList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDsc() {
        return roleDsc;
    }

    public void setRoleDsc(String roleDsc) {
        this.roleDsc = roleDsc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<PermissionDto> getPermissionDtoList() {
        return permissionDtoList;
    }

    public void setPermissionDtoList(List<PermissionDto> permissionDtoList) {
        this.permissionDtoList = permissionDtoList;
    }
}
