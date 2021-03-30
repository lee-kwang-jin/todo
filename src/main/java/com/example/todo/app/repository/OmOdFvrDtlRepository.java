package com.example.todo.app.repository;

import com.example.todo.app.table.OmOdFvrDtl;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface OmOdFvrDtlRepository extends ReactiveCrudRepository<OmOdFvrDtl, String> {
    @Query("select to_char(now(), 'YYYYMMDD') || nextval('om_od_fvr_dtl_od_fvr_no_seq')")
    Mono<String> getOdFvrNo();

    Flux<OmOdFvrDtl> findByOdNoAndOdSeq(String odNo, Integer odSeq);

    @Modifying
    @Query("update om_od_fvr_dtl set cncl_qty = :cnclQty where od_fvr_no = :odFvrNo")
    Mono<Integer> setFixCnclQtyFor(Integer cnclQty, String odFvrNo);
}
