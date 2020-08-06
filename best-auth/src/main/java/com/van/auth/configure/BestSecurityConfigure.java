package com.van.auth.configure;

import com.van.auth.service.BestUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 用以处理和令牌相关的请求
 * @author van.shu
 * @create 2020/8/6 14:25
 */
@Order(2) //这里配置顺序是让其在资源服务配置之前生效
@EnableWebSecurity
public class BestSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    private BestUserDetailService bestUserDetailService;

    /**
     * 密码配置
     * @return Spring Security内部
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 密码模式需要用的
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 对oauth接口进行验证
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                //下面配置的规则只对/oauth 开头的请求有效
                .antMatchers("/oauth/**")
                .and()
                .authorizeRequests().antMatchers("/oauth/**").authenticated()
                .and().csrf().disable();

    }

    /**
     * 指定userDetailsService和passwordEncoder
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //TODO
        auth.userDetailsService(bestUserDetailService).passwordEncoder(passwordEncoder());

    }
}
