package com.example.todo.app.handler;

import com.example.todo.app.repository.TodoInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TodoHandler {

    @Autowired
    TodoInfoRepository repository;

//    Mono<ServerResponse> saveTodo(ServerRequest request) {
//        return ServerResponse.ok().build(repository.save(request.));
//
//
//    }
}
