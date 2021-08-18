package com.yongsui.service;

import com.yongsui.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;


/**
 * @Description: 用户service
 * @Author: tengmingfa
 * @Date: 2021年07月27日
 */
public interface UserService extends UserDetailsService {

    // 通过邮箱查用户
    UserDto findUserByEmail(String email);
}
