package com.example.todo.config.filter;

import com.example.todo.app.domain.TodoListDomain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ResolvableType;
import org.springframework.core.codec.DataBufferDecoder;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.PooledDataBuffer;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ReactiveHttpInputMessage;
import org.springframework.http.ResponseCookie;
import org.springframework.http.codec.DecoderHttpMessageReader;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.DispatcherHandler;
import org.springframework.web.reactive.function.BodyExtractor;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.handler.WebFluxResponseStatusExceptionHandler;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;

@Component
@Slf4j
public class CustomWebFilter implements WebFilter {
//  webfilter를 왜 써야하는가?
//  1. mvc의 interceptor를 대신해서 사용 가능.(전처리만 가능.)

//  언제 사용
//  1. corswebfiletr
//  2. 인증, 인가 처리
//  3. bad Request 생성 필요 시(header 및 쿠키 검증).
//  4. response 세션 및 쿠키 처리.

    private BodyExtractor.Context context;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        log.debug("custom web filter start");
        ServerHttpResponse response = exchange.getResponse();
        ServerHttpRequest request = exchange.getRequest();

//        request.getPath();
//        if(!request.getPath().value().contains("todo")) {
//            response.setStatusCode(HttpStatus.BAD_REQUEST);
//        }

        // requestBody를 어떻게 가져와서 사용이 가능한가!?


        response.getHeaders().add("WebFilterTest", "test");
        response.getCookies().add("cookieTest", ResponseCookie.from("test", "test").build());
//                .subscribe(System.out::println);
//        ResolvableType test = ResolvableType.forClass(TodoListDomain.class);

//        ServerRequest;::
//        ServerResponse
        return chain.filter(exchange).doFinally(signalType -> {
            log.debug("after all ==> : ", signalType);
        });
    }
}
