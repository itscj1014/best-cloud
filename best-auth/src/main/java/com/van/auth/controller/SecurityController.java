package com.van.auth.controller;

import com.van.commons.entity.BestResponse;
import com.van.commons.exception.BestAuthException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author van.shu
 * @create 2020/8/6 16:28
 */
@RestController
public class SecurityController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;


    @GetMapping("/oauth/test")
    public String testOauth() {
        return "oauth";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @DeleteMapping("signout")
    public BestResponse signout(HttpServletRequest request) throws BestAuthException {
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.replace(authorization, "bearer ", "");
        boolean resut = consumerTokenServices.revokeToken(token);
        BestResponse response = new BestResponse();
        if (!resut) {
            throw new BestAuthException("退出登录失败");
        }

        return response.message("退出登录成功");
    }
}
