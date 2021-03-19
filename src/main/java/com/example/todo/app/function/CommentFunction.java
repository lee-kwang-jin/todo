package com.example.todo.app.function;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class CommentFunction<T, R, E extends Repository> {

    public Function<T, R> getComment(E e) {
        T t;

        return null;
    }
}
