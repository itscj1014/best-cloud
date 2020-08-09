package com.van.commons.annotation;

import com.van.commons.configure.BestLettuceRedisConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(BestLettuceRedisConfigure.class)
public @interface EnableBestLettuceRedis {

}