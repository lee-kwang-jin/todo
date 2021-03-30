package com.example.todo.app.repository;

import com.example.todo.app.domain.OmOdDtlKeys;
import com.example.todo.app.table.OmOdDtl;
import com.example.todo.config.handler.RepositoryBeforeSaveCallBackService;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface OmOdDtlRepository extends ReactiveCrudRepository<OmOdDtl, String> {

    Mono<OmOdDtl> getOmOdDtlByOdNoAndOdSeqAndProcSeq(String odNo, Integer odSeq, Integer procSeq);

    @Query("select to_char(now(), 'YYYYMMDD') || nextval('om_od_dtl_clm_no_seq')")
    Mono<String> getClmNo();

    @Query("select max(proc_seq) from om_od_dtl where od_no = :odNo and od_seq = :odSeq")
    Mono<Integer> getMaxProcSeq(String odNo, Integer odSeq);

    @Modifying
    @Query("update om_od_dtl set cncl_qty = :odQty where od_no = :odNo and od_seq = :odSeq and proc_seq = :procSeq")
    Mono<Integer> setFixCnclQtyFor(Integer odQty, String odNo, Integer odSeq, Integer procSeq);
}
