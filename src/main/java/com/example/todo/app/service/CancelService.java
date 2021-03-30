package com.example.todo.app.service;

import com.example.todo.app.table.OmOdDtl;
import reactor.core.publisher.Flux;

import java.util.List;

public interface CancelService {
    Flux<OmOdDtl> orderCancel(List<OmOdDtl> cancelList);
}
