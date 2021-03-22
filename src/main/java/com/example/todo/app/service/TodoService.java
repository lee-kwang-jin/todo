package com.example.todo.app.service;

import com.example.todo.app.dto.request.TodoSaveReq;
import com.example.todo.app.dto.response.TodoInfoRes;
import com.example.todo.app.dto.response.TodoListRes;
import com.example.todo.app.dto.response.TodoProjectionDto;
import com.example.todo.app.projection.TdInfoCloseProjection;
import com.example.todo.app.projection.TdInfoOpenProjection;
import com.example.todo.app.table.TdComInfo;
import com.example.todo.app.table.TdInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TodoService {
    Flux<TodoListRes> getTodoList();

    Flux<TodoInfoRes> getTodoListFromDataClient();

    Flux<TdInfo> getTodoListForEntity();

    Flux<TdInfo> getTodoListForEntityByMap();

    Mono<TdComInfo> saveTodo(TodoSaveReq req);

    Flux<TdInfoOpenProjection> findTodoListWithProjection();

    Flux<TdInfoOpenProjection> findTodoListByTdIdWithOpenProjection(Integer tdId);

    Flux<TdInfoCloseProjection> readTodoListWithCloseProjection();

    Flux<TdInfoCloseProjection> readTodoListByTdIdWithCloseProjection(Integer tdId);

    Flux<TodoProjectionDto> getTodoListWithDtoProjection();

    Flux<TodoProjectionDto> getTodoListByTdContWithDtoProjection(String tdCont);

    Flux<TodoProjectionDto> searchTodoListWithDtoProjection();

    Flux<TodoProjectionDto> searchTodoListByTdIdWithDtoProjection(Integer tdId);
}
