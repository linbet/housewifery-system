package com.yongsui;

import com.yongsui.config.RsaKeyProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
@MapperScan("com.yongsui.mapper")
public class HousewiferyApplication {
    public static void main(String[] args) {
        SpringApplication.run(HousewiferyApplication.class,args);
    }
}
