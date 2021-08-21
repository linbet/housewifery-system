package com.yongsui.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

/**
 * @Description: 权限传输类
 * @Author: tengmingfa
 * @Date: 2021年07月28日
 */
public class PermissionDto implements GrantedAuthority {

    private Long id;

    private String permissionName;

    private String permissionDsc;

    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @JsonIgnore
    @Override
    public String getAuthority() {
        return permissionName;
    }
}
