package com.example.todo.app.controller;

import com.example.todo.app.domain.TodoListDomain;
import com.example.todo.app.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    TodoListService service;

    @GetMapping("/list")
    public Flux<TodoListDomain> getTodoList() {
        return service.findAll()
                .map(data -> {
                    data.setModDtime(LocalDateTime.now());
                    return data;
                });
    }

    @PostMapping("/save")
    public Mono<TodoListDomain> saveTodo(@RequestBody TodoListDomain todo) {
        return service.save(todo);
    }
}
