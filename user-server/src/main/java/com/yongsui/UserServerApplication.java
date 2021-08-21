package com.yongsui;

import com.yongsui.config.RsaKeyProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@MapperScan("com.yongsui.mapper")
public class UserServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class,args);
    }
}
