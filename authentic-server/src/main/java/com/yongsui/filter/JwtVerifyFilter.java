package com.yongsui.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yongsui.config.RsaKeyProperties;
import com.yongsui.domain.Payload;
import com.yongsui.dto.UserDto;
import com.yongsui.utils.JwtUtils;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class JwtVerifyFilter extends BasicAuthenticationFilter {

    private RsaKeyProperties prop;

    public JwtVerifyFilter(AuthenticationManager authenticationManager,RsaKeyProperties prop) {
        super(authenticationManager);
        this.prop = prop;
    }

    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer ")) {
            // 如果携带正确格式token
            try{
                String token = header.replace("Bearer ","");
                // 验证token是否正确
                Payload<UserDto> payload = JwtUtils.getInfoFromToken(token, prop.getPublicKey(), UserDto.class);
                UserDto user = payload.getUserInfo();
                if(user != null){
                    UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken(user.getEmail(), null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authResult);
                }
                chain.doFilter(request, response);
            }catch(Exception e){
                response.setContentType("application/json;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                PrintWriter out = response.getWriter();
                Map resultMap = new HashMap();
                resultMap.put("code",HttpServletResponse.SC_UNAUTHORIZED);
                resultMap.put("msg","身份验证过期或错误，请重新登录！");
                out.write(new ObjectMapper().writeValueAsString(resultMap));
                out.flush();
                out.close();
            }
        } else {
            // 如果没有认证，则给用户提示请登录
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            PrintWriter out = response.getWriter();
            Map resultMap = new HashMap();
            resultMap.put("code",HttpServletResponse.SC_FORBIDDEN);
            resultMap.put("msg","请登录！");
            out.write(new ObjectMapper().writeValueAsString(resultMap));
            out.flush();
            out.close();
        }
    }


// 这是教程原码，上面是我改进后的代码
//    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        String header = request.getHeader("Authorization");
//        if (header == null && !header.startsWith("Bearer ")) {
//            // 如果没有认证，则给用户提示请登录
//            response.setContentType("application/json;charset=utf-8");
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            PrintWriter out = response.getWriter();
//            Map resultMap = new HashMap();
//            resultMap.put("code",HttpServletResponse.SC_FORBIDDEN);
//            resultMap.put("msg","请登录！");
//            out.write(new ObjectMapper().writeValueAsString(resultMap));
//            out.flush();
//            out.close();
//            chain.doFilter(request, response);
//        } else {
//            // 如果携带正确格式token
//            String token = header.replace("Bearer ","");
//            // 验证token是否正确
//            Payload<UserDto> payload = JwtUtils.getInfoFromToken(token, prop.getPublicKey(), UserDto.class);
//            UserDto user = payload.getUserInfo();
//            if(user != null){
//                UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken(user.getEmail(), null, user.getAuthorities());
//                SecurityContextHolder.getContext().setAuthentication(authResult);
//            }else {
//                System.out.println("token错误");
//            }
//            chain.doFilter(request, response);
//        }
//    }

}
