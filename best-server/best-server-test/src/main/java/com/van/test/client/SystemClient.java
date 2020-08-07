package com.van.test.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author van.shu
 * @create 2020/8/7 14:45
 */

@FeignClient(name = "best-system")
public interface SystemClient {

    @GetMapping("/hello")
    String hello(@RequestParam("name") String name);
}
