package com.yongsui.controller;

import com.yongsui.domain.Feedback;
import com.yongsui.domain.FeedbackEnum;
import com.yongsui.dto.UserDto;
import com.yongsui.service.UserService;
import com.yongsui.utils.EmptyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private Feedback fb = new Feedback();

    @Autowired
    private UserService userService;

    // 通过email查询用户
    @GetMapping("findUserByEmail")
    public UserDto findUserByEmail(String email){
        System.out.println("资源服务器");
        return userService.findUserByEmail(email);
    }

    // 新增用户
    @PostMapping("/addUser")
    @PreAuthorize("hasAnyAuthority('addUser','write')")
    public Feedback addUser(UserDto userDto){
        if(!EmptyUtils.isEmpty(userDto) &&
                !EmptyUtils.isEmpty(userDto.getEmail()) &&
                !EmptyUtils.isEmpty(userDto.getPassword()) &&
                !EmptyUtils.isEmpty(userDto.getUsername()) &&
                !EmptyUtils.isEmpty(userDto.getRoleId())
        ){
            fb.setFeedback(FeedbackEnum.SUCCESS);
            Boolean result = userService.addUser(userDto);
            fb.setData("result",result);
            return fb;
        }else{
            logger.error("addUser接口参数异常！");
            fb.setFeedback(FeedbackEnum.FAIL);
            return fb;
        }

    }


}
