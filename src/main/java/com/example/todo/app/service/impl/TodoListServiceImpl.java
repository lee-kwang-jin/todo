package com.example.todo.app.service.impl;

import com.example.todo.app.domain.TodoListDomain;
import com.example.todo.app.repository.TodoListRepository;
import com.example.todo.app.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class TodoListServiceImpl implements TodoListService {

    @Autowired
    TodoListRepository repository;

    @Override
    public Flux<TodoListDomain> findAll() {
        return repository.findAll();
    }
}
