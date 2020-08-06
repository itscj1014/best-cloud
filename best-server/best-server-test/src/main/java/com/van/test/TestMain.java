package com.van.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author van.shu
 * @create 2020/8/6 20:50
 */
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TestMain {
    public static void main(String[] args) {
        SpringApplication.run(TestMain.class, args);
    }

}
