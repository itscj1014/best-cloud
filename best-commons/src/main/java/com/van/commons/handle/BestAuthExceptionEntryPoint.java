package com.van.commons.handle;

import com.alibaba.fastjson.JSONObject;
import com.van.commons.entity.BestResponse;
import com.van.commons.utils.BestUtil;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理资源服务器401异常
 *
 * @author van.shu
 * @create 2020/8/7 9:15
 */
public class BestAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        BestResponse bestResponse = new BestResponse();
        bestResponse.message("token无效");

        BestUtil.makeResponse(response, MediaType.APPLICATION_JSON_UTF8_VALUE, HttpServletResponse.SC_UNAUTHORIZED, bestResponse);

    }
}
