package com.yongsui.controller;

import com.yongsui.domain.Feedback;
import com.yongsui.domain.FeedbackEnum;
import com.yongsui.dto.RoleDto;
import com.yongsui.service.RoleService;
import com.yongsui.utils.EmptyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 角色管理相关接口
 * @Author: tengmingfa
 * @Date: 2021年08月21日
 */

@RestController
@RequestMapping("/role")
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    Feedback fb = new Feedback();

    @Autowired
    RoleService roleService;

    // 新增用户
    @PostMapping("/findRole")
    @PreAuthorize("hasAnyAuthority('addUser','write')")
    public Feedback findRole(Integer roleId){
        if(!EmptyUtils.isEmpty(roleId)){
            RoleDto roleDto = roleService.findRoleByRoleId(roleId);
            fb.setFeedback(FeedbackEnum.SUCCESS);
            fb.setData("roleDto",roleDto);
            return fb;
        }else{
            logger.error("findRole接口参数异常！");
            fb.setFeedback(FeedbackEnum.FAIL);
            return fb;
        }

    }

}
