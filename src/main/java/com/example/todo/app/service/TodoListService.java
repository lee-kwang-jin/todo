package com.example.todo.app.service;

import com.example.todo.app.domain.TodoListDomain;
import reactor.core.publisher.Flux;

public interface TodoListService {
    Flux<TodoListDomain> findAll();
}
