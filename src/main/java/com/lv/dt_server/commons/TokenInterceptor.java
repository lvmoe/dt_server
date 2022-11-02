package com.lv.dt_server.commons;


import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    private static JwtUtils jwtUtils;

    @Resource
    private void setJwtUtil(JwtUtils jwtUtil) {
        jwtUtils = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String authorization = request.getHeader("Authorization");
        if (StringUtils.hasLength(authorization)) {
            String userName = jwtUtils.verifyTokenAndGetUserName(authorization);
            request.setAttribute("userName", userName);
            return StringUtils.hasLength(userName);
        } else {
            return false;
        }
    }
}