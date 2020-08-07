package com.van.zuul.filter;

import com.netflix.zuul.context.RequestContext;
import com.van.commons.entity.BestResponse;
import com.van.commons.utils.BestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;

/**
 * 自定义zuul异常处理
 * @author van.shu
 * @create 2020/8/7 10:22
 */
@Component
@Slf4j
public class BestGatewayErrorFilter extends SendErrorFilter {

    @Override
    public Object run() {
        try {

            BestResponse bestResponse = new BestResponse();

            RequestContext ctx = RequestContext.getCurrentContext();
            String serviceId = (String) ctx.get(FilterConstants.SERVICE_ID_KEY);

            ExceptionHolder exception = findZuulException(ctx.getThrowable());

            String errorCause = exception.getErrorCause();
            Throwable throwable = exception.getThrowable();
            String message = throwable.getMessage();

            message = StringUtils.isEmpty(message) ? errorCause : message;

            resolveExceptionMessage(message, serviceId, bestResponse);

            HttpServletResponse response = ctx.getResponse();

            BestUtil.makeResponse(response, MediaType.APPLICATION_JSON_UTF8_VALUE, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, bestResponse);

            log.error("zuul sendError:{}", bestResponse.getMessage());


        } catch (Exception ex) {
            log.error("zuul sendError:", ex);
            ReflectionUtils.rethrowRuntimeException(ex);
        }

        return null;
    }

    private BestResponse resolveExceptionMessage(String message, String serviceId, BestResponse bestResponse) {

        if (StringUtils.containsIgnoreCase(message, "time out")) {
            return bestResponse.message("请求" + serviceId + "服务超时");
        }

        if (StringUtils.containsIgnoreCase(message, "forwarding error")) {
            return bestResponse.message(serviceId + "服务不可用");
        }

        return bestResponse.message("zuul请求" + serviceId + "服务异常");
    }
}
