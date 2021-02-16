package com.innovationchef.controller;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@Component
public class ReqLogFilter extends AbstractRequestLoggingFilter {

    public ReqLogFilter() {
        this.setIncludeClientInfo(true);
        this.setIncludeQueryString(true);
        this.setIncludeHeaders(true);
        this.setIncludePayload(true);
        this.setMaxPayloadLength(100000);
        this.setBeforeMessagePrefix("Before API Handler [");
        this.setBeforeMessageSuffix("]");
        this.setAfterMessagePrefix("After API Handler [");
        this.setAfterMessageSuffix("]");
    }

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        return log.getLevel().equals(Level.DEBUG);
    }

    @Override
    protected void beforeRequest(HttpServletRequest httpServletRequest, String s) {
        log.info(s);
    }

    @Override
    protected void afterRequest(HttpServletRequest httpServletRequest, String s) {
        log.info(s);
    }
}
