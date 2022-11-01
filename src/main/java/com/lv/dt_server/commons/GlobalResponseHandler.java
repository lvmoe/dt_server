package com.lv.dt_server.commons;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice(annotations = {ResponseResult.class})
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        final String returnTypeName = methodParameter.getParameterType().getName();
        return !"com.lv.dt_server.commons.Result".equals(returnTypeName) && !"org.springframework.http.ResponseEntity".equals(returnTypeName);
    }

    @Override
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        final String returnTypeName = methodParameter.getParameterType().getName();
        if ("void".equals(returnTypeName)) {
            return Result.success(null);
        }
        if (!mediaType.includes(MediaType.APPLICATION_JSON)) {
            return o;
        }
        if ("com.lv.dt_server.commons.Result".equals(returnTypeName)) {
            return o;
        }
        /*
         * 处理异常,判断body是否为异常类。
         * o instanceof Exception
         * */
        return Result.success(o);
    }
}