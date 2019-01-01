package com.auto.parking.autoparking.Interceptor;

import com.auto.parking.autoparking.service.RequestRateLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interceptor to limit too many requests from client.
 */
@Component
public class RequestRateLimitInterceptor extends HandlerInterceptorAdapter {

    //Disable by default as this is a demo.
    private boolean enabled = false;

    private final RequestRateLimitService requestRateLimitService;

    @Autowired
    public RequestRateLimitInterceptor(RequestRateLimitService requestRateLimitService) {this.requestRateLimitService = requestRateLimitService;}

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!enabled) {
            return true;
        }
        final String clientId = request.getHeader("Client-Id");
        final Boolean consumeRequest = requestRateLimitService.consumeRequest(String.valueOf(clientId));
        if (!consumeRequest) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        }
        return consumeRequest;
    }
}
