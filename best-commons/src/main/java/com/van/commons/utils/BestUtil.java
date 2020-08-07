package com.van.commons.utils;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author van.shu
 * @create 2020/8/7 9:20
 */
public class BestUtil {

    private BestUtil(){

    }


    /**
     * 设置响应
     *
     * @param response    响应
     * @param contentType content-type
     * @param status      http 状态码
     * @param value       响应内容
     */
    public static void makeResponse(HttpServletResponse response, String contentType, int status, Object value) throws IOException {

        response.setContentType(contentType);
        response.setStatus(status);
        response.getOutputStream().write(JSONObject.toJSONString(value).getBytes());

    }





}
