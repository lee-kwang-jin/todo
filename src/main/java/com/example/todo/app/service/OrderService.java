package com.example.todo.app.service;

import com.example.todo.app.dto.response.OrderFavorInfo;
import com.example.todo.app.table.OmOd;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {

    Mono<OmOd> saveOrder(OmOd req);

    Flux<OrderFavorInfo> getOrderByFavor();
}
