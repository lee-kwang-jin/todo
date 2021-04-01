package com.example.todo.app.controller;

import com.example.todo.app.dto.request.TodoSaveReq;
import com.example.todo.app.dto.response.TodoInfoRes;
import com.example.todo.app.dto.response.TodoListRes;
import com.example.todo.app.dto.response.TodoProjectionDto;
import com.example.todo.app.projection.TdInfoCloseProjection;
import com.example.todo.app.projection.TdInfoOpenProjection;
import com.example.todo.app.service.TodoService;
import com.example.todo.app.table.TdComInfo;
import com.example.todo.app.table.TdInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    TodoService todoService;

    @GetMapping("/list")
    public Flux<TodoListRes> getTodoList() {
        return todoService.getTodoList();
    }

    @GetMapping("/client/list")
    public Flux<TodoInfoRes> getTodoClientList() {
        return todoService.getTodoListFromDataClient();
    }

    @GetMapping("/entity/list")
    public Flux<TdInfo> getTodoListForEntity() {
        return todoService.getTodoListForEntity();
    }

    @GetMapping("/entity/map/list")
    public Flux<TdInfo> getTodoListForEntityByMap() {
        return todoService.getTodoListForEntityByMap();
    }

//    @GetMapping("/projection/open/list")
//    public Flux<TdInfoOpenProjection> findTodoListWithProjection() {
//        return todoService.findTodoListWithProjection();
//    }
//
//    @GetMapping("/projection/open/{tdId}")
//    public Flux<TdInfoOpenProjection> findTodoListByTdIdWithProjection(@PathVariable Integer tdId) {
//        return todoService.findTodoListByTdIdWithOpenProjection(tdId);
//    }
//
//    @GetMapping("/projection/close/list")
//    public Flux<TdInfoCloseProjection> readTodoListWithProjection() {
//        return todoService.readTodoListWithCloseProjection();
//    }
//
//    @GetMapping("/projection/close/{tdId}")
//    public Flux<TdInfoCloseProjection> readTodoListByTdIdWithProjection(@PathVariable Integer tdId) {
//        return todoService.readTodoListByTdIdWithCloseProjection(tdId);
//    }
//
//    @GetMapping("/projection/dto/list")
//    public Flux<TodoProjectionDto> getTodoListWithDtoProjection() {
//        return todoService.getTodoListWithDtoProjection();
//    }
//
//    @GetMapping("/projection/dto/{tdCont}")
//    public Flux<TodoProjectionDto> getTodoListByTdContWithDtoProjection(@PathVariable String tdCont) {
//        return todoService.getTodoListByTdContWithDtoProjection(tdCont);
//    }

    @GetMapping("/projection/dynamic/list")
    public Flux<TodoProjectionDto> searchTodoListWithDtoProjection() {
        return todoService.searchTodoListWithDtoProjection();
    }

    @GetMapping("/projection/dynamic/{tdId}")
    public Flux<TodoProjectionDto> searchTodoListWithDtoProjection(@PathVariable Integer tdId) {
        return todoService.searchTodoListByTdIdWithDtoProjection(tdId);
    }

    @PostMapping("/save")
    public Mono<TdComInfo> saveTodo(@RequestBody TodoSaveReq req) {
        return todoService.saveTodo(req);
    }
}
