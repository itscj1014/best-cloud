package com.van.commons.annotation;

import com.van.commons.configure.BestOauth2FeignConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author van.shu
 * @create 2020/8/7 15:41
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(BestOauth2FeignConfigure.class)
public @interface EnableBestOauth2FeignFeignClient {


}
