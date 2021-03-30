package com.example.todo.app.repository;

import com.example.todo.app.table.OmOd;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface OmOdRepository extends ReactiveCrudRepository<OmOd, String> {

    @Query("select to_char(now(), 'YYYYMMDD') || nextval('om_od_od_no_seq')")
    Mono<String> getOdNo();
}
