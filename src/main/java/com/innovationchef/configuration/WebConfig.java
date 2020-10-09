package com.innovationchef.configuration;

import com.innovationchef.controller.RequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private RequestInterceptor interceptor;

    public WebConfig(RequestInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Path pattern added so that swagger endpoints does not go through header request interceptor
        registry.addInterceptor(this.interceptor).addPathPatterns("/v1/**");
    }
}
