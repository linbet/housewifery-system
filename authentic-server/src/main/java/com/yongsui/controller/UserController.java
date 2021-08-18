package com.yongsui.controller;

import com.yongsui.dto.UserDto;
import com.yongsui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 用户controller
 * @Author: tengmingfa
 * @Date: 2021年07月27日
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("findUserByEmail")
    public String findUserByEmail(String email){
        UserDetails userDetails = userService.findUserByEmail(email);
        System.out.println(userDetails);
        return "访问成功";
    }


    // 注册密码
    @GetMapping("/registPassword")
    public String registPassword(String password){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

}
