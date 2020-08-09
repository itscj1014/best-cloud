package com.van.auth;

import com.van.commons.annotation.EnableBestAuthExceptionHandle;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author van.shu
 * @create 2020/8/6 14:06
 */
@SpringBootApplication
@EnableBestAuthExceptionHandle
@MapperScan("com.van.auth.mapper")
public class AuthApp {

    public static void main(String[] args) {
        SpringApplication.run(AuthApp.class, args);
    }
}
