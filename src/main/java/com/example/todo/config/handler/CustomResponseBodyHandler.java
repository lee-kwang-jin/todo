package com.example.todo.config.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.HandlerResult;
import org.springframework.web.reactive.HandlerResultHandler;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;
import org.springframework.web.reactive.result.method.annotation.AbstractMessageWriterResultHandler;
import org.springframework.web.reactive.result.method.annotation.ResponseBodyResultHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;
import java.util.List;

@Slf4j
public class CustomResponseBodyHandler extends ResponseBodyResultHandler {

    public CustomResponseBodyHandler(List<HttpMessageWriter<?>> messageWriters, RequestedContentTypeResolver contentTypeResolver) {
        super(messageWriters, contentTypeResolver);
    }

    @Override
    public boolean supports(HandlerResult result) {
        return true;
    }

    @Override
    public Mono<Void> handleResult(ServerWebExchange exchange, HandlerResult result) {
        if( result.getReturnValue() instanceof Mono ) {
            Mono<Test> resultBody = ((Mono<Object>) result.getReturnValue()).log().map(
                    d -> new Test(d, "true"))
                    .log()
                    .defaultIfEmpty(new Test("test", "true"))
                    .log();
            return writeBody(resultBody, result.getReturnTypeSource(), exchange);
        } else {
            Mono<Test> resultBody = Flux.from((Flux<Object>) result.getReturnValue())
                    .collectList()
                    .log()
                    .map(d -> new Test(d, "true"))
                    .log();

            return writeBody(resultBody, result.getReturnTypeSource(), exchange);
        }
    }

    @Data
    @AllArgsConstructor
    class Test<T> {
        private T data;
        private String success;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
