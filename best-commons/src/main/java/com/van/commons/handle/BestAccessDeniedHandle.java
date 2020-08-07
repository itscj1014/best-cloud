package com.van.commons.handle;

import com.van.commons.entity.BestResponse;
import com.van.commons.utils.BestUtil;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author van.shu
 * @create 2020/8/7 9:28
 */
public class BestAccessDeniedHandle implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {

        BestResponse bestResponse = new BestResponse();
        bestResponse.message("没有权限访问该资源");

        BestUtil.makeResponse(response, MediaType.APPLICATION_JSON_UTF8_VALUE, HttpServletResponse.SC_FORBIDDEN, bestResponse);
    }
}
