package com.van.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author van.shu
 * @create 2020/8/6 20:24
 */
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SystemApp {

    public static void main(String[] args) {
        SpringApplication.run(SystemApp.class, args);
    }
}
