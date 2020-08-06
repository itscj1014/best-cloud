package com.van.system.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author van.shu
 * @create 2020/8/6 20:29
 */
@Configuration
@EnableResourceServer
public class SystemResouceServerConfigure extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                //所有访问该应用的请求都需要认证
                .authorizeRequests().antMatchers("/**").authenticated();
    }
}
