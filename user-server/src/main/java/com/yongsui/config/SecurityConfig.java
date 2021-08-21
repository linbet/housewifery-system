package com.yongsui.config;

import com.yongsui.filter.JwtVerifyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
/**
 * 需要加@EnableWebSecurity
 * springSecurity的配置才会生效
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RsaKeyProperties prop;

    // 配置springSecurity相关的信息
    public void configure(HttpSecurity http) throws Exception {
        // 释放静态资源，指定资源拦截规则，指定自定义认证页面，指定退出认证配置,csrf配置
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
                // 设置headers，让iframe标签可以内嵌内容
                .headers()
                .frameOptions()
                .sameOrigin()
                .and()
                // 设置登出配置
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .permitAll()
                // 分割
                .and()
                .addFilter(new JwtVerifyFilter(super.authenticationManager(),prop))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

}
