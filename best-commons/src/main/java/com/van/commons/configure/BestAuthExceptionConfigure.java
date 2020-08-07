package com.van.commons.configure;

import com.van.commons.handle.BestAccessDeniedHandle;
import com.van.commons.handle.BestAuthExceptionEntryPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @author van.shu
 * @create 2020/8/7 9:34
 */
public class BestAuthExceptionConfigure {


    /**
     * 当IOC容器中没有指定的类型或者名称的Bean时，就注册它
     * 这么做的好处就是子系统可以自定义自己的资源服务器异常处理器来覆盖我们这里的
     * @return BestAccessDeniedHandle
     */
    @Bean
    @ConditionalOnMissingBean(name = {"accessDeniedHandle"})
    public BestAccessDeniedHandle accessDeniedHandle() {
        return new BestAccessDeniedHandle();
    }

    @Bean
    @ConditionalOnMissingBean(name = {"authExceptionEntryPoint"})
    public BestAuthExceptionEntryPoint authExceptionEntryPoint() {
        return new BestAuthExceptionEntryPoint();
    }
}
