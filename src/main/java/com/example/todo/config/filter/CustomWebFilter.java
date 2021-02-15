package com.example.todo.config.filter;

import com.example.todo.app.domain.TodoListDomain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class CustomWebFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        log.debug(String.valueOf(exchange.getRequest()));
        ServerHttpResponse response = exchange.getResponse();
        exchange.getRequest().getBody();
        return chain.filter(exchange);
    }
}
