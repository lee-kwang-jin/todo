package com.example.todo.config.advice;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;
import org.springframework.web.reactive.result.method.annotation.ResponseBodyResultHandler;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityResultHandler;

import java.util.List;

public class ResponseBodyAdvice extends ResponseBodyResultHandler {

    public ResponseBodyAdvice(List<HttpMessageWriter<?>> writers, RequestedContentTypeResolver resolver) {
        super(writers, resolver);
    }
}
