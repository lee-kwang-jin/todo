package com.example.todo.app.service;

import com.example.todo.app.dto.request.TodoSaveReq;
import com.example.todo.app.dto.response.TodoInfoRes;
import com.example.todo.app.dto.response.TodoListRes;
import com.example.todo.app.table.TdComInfo;
import com.example.todo.app.table.TdInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TodoService {
    Flux<TodoListRes> getTodoList();

    Flux<TodoInfoRes> getTodoListFromDataClient();

    Flux<TdInfo> getTodoListForEntity();

    Flux<TdInfo> getTodoListForEntityByMap();

    Mono<TdComInfo> saveTodo(TodoSaveReq req);
}
