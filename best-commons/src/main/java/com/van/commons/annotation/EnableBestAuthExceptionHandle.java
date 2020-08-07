package com.van.commons.annotation;

import com.van.commons.configure.BestAuthExceptionConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author van.shu
 * @create 2020/8/7 9:42
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(BestAuthExceptionConfigure.class)
public @interface EnableBestAuthExceptionHandle {
}
