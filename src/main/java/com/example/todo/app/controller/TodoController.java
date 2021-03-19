package com.example.todo.app.controller;

import com.example.todo.app.dto.request.TodoSaveReq;
import com.example.todo.app.dto.response.TodoInfoRes;
import com.example.todo.app.dto.response.TodoListRes;
import com.example.todo.app.service.TodoService;
import com.example.todo.app.table.TdComInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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

    @PostMapping("/save")
    public Mono<TdComInfo> saveTodo(@RequestBody TodoSaveReq req) {
        return todoService.saveTodo(req);
    }
}
