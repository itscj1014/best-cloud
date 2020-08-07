package com.van.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author van.shu
 * @create 2020/8/6 20:39
 */
@RestController
public class TestController {

    @GetMapping("hello")
    public String hello(@RequestParam("name") String name) {
        return "hello" + name;
    }

    @GetMapping("test")
    public String test() {
        return "best-server-system";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

}
