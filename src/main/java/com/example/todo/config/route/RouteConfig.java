package com.example.todo.config.route;

import com.example.todo.app.handler.OrderHandler;
import com.example.todo.app.handler.TodoHandler;
import com.example.todo.app.repository.TodoInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouteConfig {

//    @Bean
//    public RouterFunction<ServerResponse> todoRoute(TodoHandler todoHandler) {
//        return RouterFunctions.route()
////                .GET("/route/todo/list", todoHandler::getTodoList)
//                .build();
//    }
//
//    public RouterFunction<ServerResponse> orderRoute(OrderHandler orderHandler) {
//        return RouterFunctions.route()
//                .build();
//    }
}
