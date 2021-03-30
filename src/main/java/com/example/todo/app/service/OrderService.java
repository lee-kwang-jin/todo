package com.example.todo.app.service;

import com.example.todo.app.table.OmOd;
import reactor.core.publisher.Mono;

public interface OrderService {

    Mono<OmOd> saveOrder(OmOd req);
}
