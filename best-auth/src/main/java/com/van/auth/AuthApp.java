package com.van.auth;

import com.van.commons.annotation.EnableBestAuthExceptionHandle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author van.shu
 * @create 2020/8/6 14:06
 */
@SpringBootApplication
@EnableBestAuthExceptionHandle
public class AuthApp {

    public static void main(String[] args) {
        SpringApplication.run(AuthApp.class, args);
    }
}
