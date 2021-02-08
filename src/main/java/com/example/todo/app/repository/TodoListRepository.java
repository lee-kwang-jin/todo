package com.example.todo.app.repository;

import com.example.todo.app.domain.TodoListDomain;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TodoListRepository extends ReactiveCrudRepository<TodoListDomain, Integer> {

    Flux<TodoListDomain> findAll();

}
