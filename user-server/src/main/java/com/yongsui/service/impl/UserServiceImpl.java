package com.yongsui.service.impl;

import com.yongsui.dto.UserDto;
import com.yongsui.entity.User;
import com.yongsui.mapper.UserMapper;
import com.yongsui.service.UserService;
import com.yongsui.utils.EmptyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Description: 用户服务的实现类，实现用户管理相关的逻辑
 * @Author: tengmingfa
 * @Date: 2021年08月21日
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDto findUserByEmail(String email) {
        User user = userMapper.findUserByEmail(email);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        return userDto;
    }

    @Override
    public Boolean addUser(UserDto userDto) {
        if(!EmptyUtils.isEmpty(userDto) &&
                !EmptyUtils.isEmpty(userDto.getEmail()) &&
                !EmptyUtils.isEmpty(userDto.getPassword()) &&
                !EmptyUtils.isEmpty(userDto.getUsername())
        ){
            // 密码加密
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String password = bCryptPasswordEncoder.encode(userDto.getPassword());
            userDto.setPassword(password);

            User user = new User();
            BeanUtils.copyProperties(userDto,user);
            return userMapper.addUser(user);
        }
        return false;
    }


}
