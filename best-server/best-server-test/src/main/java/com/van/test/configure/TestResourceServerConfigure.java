package com.van.test.configure;

import com.van.commons.handle.BestAccessDeniedHandle;
import com.van.commons.handle.BestAuthExceptionEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author van.shu
 * @create 2020/8/6 20:55
 */
@Configuration
@EnableResourceServer
public class TestResourceServerConfigure extends ResourceServerConfigurerAdapter {


    @Autowired
    private BestAuthExceptionEntryPoint authExceptionEntryPoint;

    @Autowired
    private BestAccessDeniedHandle accessDeniedHandle;


    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers("/**").authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.accessDeniedHandler(accessDeniedHandle).authenticationEntryPoint(authExceptionEntryPoint);
    }
}
