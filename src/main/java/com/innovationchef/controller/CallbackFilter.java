package com.innovationchef.controller;

import com.innovationchef.callback.Activity;
import com.innovationchef.callback.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class CallbackFilter implements Filter {

    @Autowired
    private Callback callback;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        Activity activity = Activity.register();
        filterChain.doFilter(req, res);
        activity = activity.finished()
                .requestedEndpoint(req.getServletPath())
                .responseStatus(HttpStatus.valueOf(res.getStatus()));
        this.callback.publish(activity);
    }
}
