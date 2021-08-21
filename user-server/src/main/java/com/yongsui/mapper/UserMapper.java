package com.yongsui.mapper;

import com.yongsui.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    // 通过email查询用户
    User findUserByEmail(@Param("email") String email);

    // 添加用户
    Boolean addUser(User user);

}
