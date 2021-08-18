package com.yongsui.entity;

import java.io.Serializable;

/**
 * @Description: 角色实体类
 * @Author: tengmingfa
 * @Date: 2021年07月28日
 */
public class Role implements Serializable {

    private static final long serialVersionUID = -5103002464464323789L;

    private Long id;

    private String roleName;

    private String roleDsc;

    private Integer status;

    private String createTime;

    private String updateTime;

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
