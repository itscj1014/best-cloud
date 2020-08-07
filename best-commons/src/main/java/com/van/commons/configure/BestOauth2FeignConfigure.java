package com.van.commons.configure;


import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;


/**
 * @author van.shu
 * @create 2020/8/7 15:02
 */
@Slf4j
public class BestOauth2FeignConfigure {

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {

        return restTemplate -> {
            SecurityContext context = SecurityContextHolder.getContext();
            Object details = context.getAuthentication().getDetails();

            if (details instanceof OAuth2AuthenticationDetails) {
                String authorizationToken = ((OAuth2AuthenticationDetails) details).getTokenValue();
                log.info("authorizationToken的值是:{}", authorizationToken);
                restTemplate.header(HttpHeaders.AUTHORIZATION, "bearer " + authorizationToken);
            }
        };

    }
}
