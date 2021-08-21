package com.yongsui.service;

import com.yongsui.dto.UserDto;

public interface UserService {

    // 通过email查询用户
    UserDto findUserByEmail(String email);

    // 添加用户
    Boolean addUser(UserDto userDto);
}
