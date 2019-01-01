package com.auto.parking.autoparking.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final HandlerInterceptorAdapter limiter;

    @Autowired
    public WebMvcConfig(HandlerInterceptorAdapter limiter) {this.limiter = limiter;}

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(limiter);
    }
}