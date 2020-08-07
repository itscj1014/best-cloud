package com.van.system.configure;

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
 * @create 2020/8/6 20:29
 */
@Configuration
@EnableResourceServer
public class SystemResouceServerConfigure extends ResourceServerConfigurerAdapter {

    @Autowired
    private BestAccessDeniedHandle accessDeniedHandle;

    @Autowired
    private BestAuthExceptionEntryPoint authExceptionEntryPoint;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                //所有访问该应用的请求都需要认证
                .authorizeRequests().antMatchers("/**").authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.authenticationEntryPoint(authExceptionEntryPoint).accessDeniedHandler(accessDeniedHandle);
    }
}
