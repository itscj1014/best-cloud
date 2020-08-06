package com.van.test.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author van.shu
 * @create 2020/8/6 20:54
 */
@RestController
public class TestController {


    @GetMapping("test1")
    @PreAuthorize("hasAnyAuthority('user:add')")
    public String test1() {
        return "test1 from test server";
    }

    @GetMapping("test2")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    public String test2() {
        return "test2 from test server";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }
}
