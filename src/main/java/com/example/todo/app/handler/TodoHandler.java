package com.example.todo.app.handler;

import com.example.todo.app.projection.TdInfoOpenProjection;
import com.example.todo.app.repository.TodoInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class TodoHandler {

    @Autowired
    TodoInfoRepository repository;

//    public Mono<ServerResponse> getTodoList(ServerRequest request) {
//        return ServerResponse.ok().body(repository.findAllBy(), TdInfoOpenProjection.class);
//    }
}
