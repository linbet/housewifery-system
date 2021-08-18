package com.yongsui.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yongsui.config.RsaKeyProperties;
import com.yongsui.dto.PermissionDto;
import com.yongsui.dto.RoleDto;
import com.yongsui.dto.UserDto;
import com.yongsui.utils.JwtUtils;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private RsaKeyProperties prop;

    public JwtLoginFilter(AuthenticationManager authenticationManager, RsaKeyProperties prop) {
        this.authenticationManager = authenticationManager;
        this.prop = prop;
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        if (MediaType.APPLICATION_JSON_VALUE.equals(request.getContentType())) {
            // json格式传参
            UserDto userDto = null;
            try{
                userDto = new ObjectMapper().readValue(request.getInputStream(),UserDto.class);
            }catch (Exception e){
                e.printStackTrace();
            }
            if(userDto == null){
                userDto = new UserDto();
            }
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword());
            return authenticationManager.authenticate(authRequest);
        }else{
            // form表单传参
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(email, password);
            return authenticationManager.authenticate(authRequest);
        }


// 这是教程原码，上面是我改进后的代码
//        try{
//            UserDto userDto = new ObjectMapper().readValue(request.getInputStream(),UserDto.class);
//            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword());
//            return authenticationManager.authenticate(authRequest);
//        }catch(Exception e){
//            try{
//                response.setContentType("application/json;charset=utf-8");
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                PrintWriter out = response.getWriter();
//                Map resultMap = new HashMap();
//                resultMap.put("code",HttpServletResponse.SC_UNAUTHORIZED);
//                resultMap.put("msg","用户名或密码错误！");
//                out.write(new ObjectMapper().writeValueAsString(resultMap));
//                out.flush();
//                out.close();
//            }catch (Exception outEx){
//                outEx.printStackTrace();
//            }
//            throw new RuntimeException(e);
//        }
    }

    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDto userDto = new UserDto();
        userDto.setEmail(authResult.getName());
        RoleDto roleDto = new RoleDto();
        roleDto.setPermissionDtoList((List<PermissionDto>) authResult.getAuthorities());
        userDto.setRole(roleDto);
        // 生成token
        String token = JwtUtils.generateTokenExpireInMinutes(userDto,prop.getPrivateKey(),24*60);

        response.addHeader("Authorization","Bearer " + token);

        try{
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter out = response.getWriter();
            Map resultMap = new HashMap();
            resultMap.put("code",HttpServletResponse.SC_OK);
            resultMap.put("msg","认证通过！");
            out.write(new ObjectMapper().writeValueAsString(resultMap));
            out.flush();
            out.close();
        }catch (Exception outEx){
            outEx.printStackTrace();
        }
    }


    public void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        try{
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            PrintWriter out = response.getWriter();
            Map resultMap = new HashMap();
            resultMap.put("code",HttpServletResponse.SC_UNAUTHORIZED);
            resultMap.put("msg","用户名或密码错误！");
            out.write(new ObjectMapper().writeValueAsString(resultMap));
            out.flush();
            out.close();
        }catch (Exception outEx){
            outEx.printStackTrace();
        }
    }

}
