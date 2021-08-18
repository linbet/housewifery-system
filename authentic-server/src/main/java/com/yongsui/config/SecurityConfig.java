package com.yongsui.config;

import com.yongsui.filter.JwtLoginFilter;
import com.yongsui.filter.JwtVerifyFilter;
import com.yongsui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 需要加@EnableWebSecurity
 * springSecurity的配置才会生效
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private RsaKeyProperties prop;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // 认证用户的来源
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }

    // 配置springSecurity相关的信息
    public void configure(HttpSecurity http) throws Exception {
        // 进行springSecurity相关配置
        http.csrf()
                .disable()
                .authorizeRequests()
                // 设置拦截路径与权限
                .antMatchers("/user/findUserByEmail").hasAnyAuthority("read")
                .anyRequest()
                .authenticated()
                // 分割
                .and()
                // 设置登录配置
                .formLogin()
                // 设置登录时的账号参数，有username、email等
                .usernameParameter("email")
                // 分割
                .and()
                // 设置登出配置
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .permitAll()
                // 分割
                .and()
                .addFilter(new JwtLoginFilter(super.authenticationManager(),prop))
                .addFilter(new JwtVerifyFilter(super.authenticationManager(),prop))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

}
