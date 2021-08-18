package com.yongsui.entity;

import java.io.Serializable;

/**
 * @Description: 权限实体类
 * @Author: tengmingfa
 * @Date: 2021年07月28日
 */
public class Permission implements Serializable {

    private static final long serialVersionUID = 4916646287147435435L;

    private Long id;

    private String permissionName;

    private String permissionDsc;

    private Long roleId;

    private Integer status;

    private String createTime;

    private String updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionDsc() {
        return permissionDsc;
    }

    public void setPermissionDsc(String permissionDsc) {
        this.permissionDsc = permissionDsc;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
