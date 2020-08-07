package com.van.commons.handle;

import com.van.commons.entity.BestResponse;
import com.van.commons.exception.BestAuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 对于通用的异常捕捉可以在BaseExceptionHandle中，而当前微服务独有的异常可以在BaseExceptionHandle的子类GlobalExceptionHandler中定义
 * @author van.shu
 * @create 2020/8/7 11:01
 */
@Slf4j
public class BaseExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BestResponse handleException(Exception e) {
        log.error("系统内部异常，异常信息", e);
        return new BestResponse().message("系统内部异常");
    }

    @ExceptionHandler(value = BestAuthException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BestResponse handleFebsAuthException(BestAuthException e) {
        log.error("系统错误", e);
        return new BestResponse().message(e.getMessage());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public BestResponse handleAccessDeniedException(){
        return new BestResponse().message("没有权限访问该资源");
    }

}
