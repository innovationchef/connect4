package com.innovationchef.controller;

import com.innovationchef.constant.ApiConstant;
import com.innovationchef.exception.ApiHeaderMissingException;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@Component
public class RequestInterceptor implements HandlerInterceptor {

    private static final String START_TIME = "start-time";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String trackingId = request.getHeader(ApiConstant.TRACKING_ID);
        request.setAttribute(START_TIME, System.currentTimeMillis());
        if (trackingId == null) {
            throw new ApiHeaderMissingException();
        }
        ThreadContext.put(ApiConstant.LOG_CONTEXT, trackingId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long startTime = (long) request.getAttribute(START_TIME);
        log.info("Request served in: {} ms", System.currentTimeMillis() - startTime);
        ThreadContext.clearAll();
    }
}
