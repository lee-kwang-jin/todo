package com.example.todo.app.service;

import com.example.todo.app.domain.OmOdDtlDomain;
import com.example.todo.app.domain.OmOdDtlKeys;
import com.example.todo.app.table.OmOdDtl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OmOdDtlService {

    Mono<OmOdDtl> saveOmOdDtl(OmOdDtl test);

    Flux<OmOdDtl> getOmOdDtlList();

    Mono<OmOdDtl> getOmOdDtl(OmOdDtlKeys keys);
}
