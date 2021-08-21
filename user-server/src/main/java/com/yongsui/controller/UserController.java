package com.yongsui.controller;

import com.yongsui.dto.UserDto;
import com.yongsui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 用户controller
 * @Author: tengmingfa
 * @Date: 2021年07月27日
 */
@RestController
@RequestMapping("/user")
@PreAuthorize("hasAuthority('read')")
public class UserController {

    @Autowired
    private UserService userService;

    // 通过email查询用户
    @GetMapping("findUserByEmail")
    @PreAuthorize("hasAnyAuthority('read')")
    public UserDto findUserByEmail(String email){
        System.out.println("资源服务器");
        return userService.findUserByEmail(email);
    }

    // 新增用户
    @PostMapping("/addUser")
    public Boolean addUser(UserDto userDto){

        return userService.addUser(userDto);
    }


}
