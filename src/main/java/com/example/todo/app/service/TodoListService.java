package com.example.todo.app.service;

import com.example.todo.app.domain.TodoListDomain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TodoListService {
    Flux<TodoListDomain> findAll();

    Mono<TodoListDomain> save(TodoListDomain todo);
}
