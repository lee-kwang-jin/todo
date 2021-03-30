package com.example.todo.app.service.impl;

import com.example.todo.app.domain.OmOdDtlDomain;
import com.example.todo.app.domain.OmOdDtlKeys;
import com.example.todo.app.repository.OmOdDtlRepository;
import com.example.todo.app.service.OmOdDtlService;
import com.example.todo.app.table.OmOdDtl;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.r2dbc.postgresql.codec.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OmOdDtlServiceImpl implements OmOdDtlService {

    @Autowired
    OmOdDtlRepository omOdDtlRepository;

    @Override
    public Mono<OmOdDtl> saveOmOdDtl(OmOdDtl test) {
        // test.setKeys(new OmOdDtlKeys("test11", 1, 1));
        return omOdDtlRepository.save(test);
    }

    @Override
    public Flux<OmOdDtl> getOmOdDtlList() {
        return omOdDtlRepository.findAll();
    }

    @Override
    public Mono<OmOdDtl> getOmOdDtl(OmOdDtlKeys keys) {
        return omOdDtlRepository.getOmOdDtlByOdNoAndOdSeqAndProcSeq(keys.getOdNo(), keys.getOdSeq(), keys.getProcSeq());
    }
}
