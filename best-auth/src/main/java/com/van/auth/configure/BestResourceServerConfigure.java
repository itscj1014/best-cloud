package com.van.auth.configure;

import com.van.commons.handle.BestAccessDeniedHandle;
import com.van.commons.handle.BestAuthExceptionEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 资源服务安全配置
 * 用以处理非 /oauth 开头的请求，其主要用于本服务资源的保护，客户端只能通过oauth2发放的令牌从资源服务器
 * 中获取收保护的资源
 * @author van.shu
 * @create 2020/8/6 14:39
 */
@Configuration
@EnableResourceServer
public class BestResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Autowired
    private BestAccessDeniedHandle accessDeniedHandle;

    @Autowired
    private BestAuthExceptionEntryPoint authExceptionEntryPoint;

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
