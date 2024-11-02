package com.meet.uber.uberapplication.advices;


import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        List<String> allowedPrefix = List.of("/actuator", "/v3/api-docs");
        boolean found = allowedPrefix
                .stream()
                .anyMatch(prefix -> request.getURI().getPath().contains(prefix));

        if (body instanceof ApiResponse || found) {
            return body;  // Avoid wrapping if already an ApiResponse
        }

        return new ApiResponse<>(body);

    }
}

