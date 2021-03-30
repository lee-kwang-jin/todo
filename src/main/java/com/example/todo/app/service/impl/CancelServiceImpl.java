package com.example.todo.app.service.impl;

import com.example.todo.app.repository.OmOdDtlRepository;
import com.example.todo.app.repository.OmOdFvrDtlRepository;
import com.example.todo.app.service.CancelService;
import com.example.todo.app.service.OmOdDtlService;
import com.example.todo.app.table.OmOdDtl;
import com.example.todo.app.table.OmOdFvrDtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Query;
import org.springframework.data.relational.core.query.Update;
import org.springframework.data.relational.core.sql.Where;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@Service
public class CancelServiceImpl implements CancelService {

    @Autowired
    OmOdDtlRepository omOdDtlRepository;

    @Autowired
    OmOdFvrDtlRepository omOdFvrDtlRepository;

    @Autowired
    R2dbcEntityTemplate r2dbcEntityTemplate;

    @Override
    public Flux<OmOdDtl> orderCancel(List<OmOdDtl> reqList) {

        Function<OmOdDtl, Mono<OmOdDtl>> setCancelData = cancel -> Mono.just(cancel)
                .zipWith(omOdDtlRepository.getMaxProcSeq(cancel.getOdNo(), cancel.getOdSeq()), (t1, t2) -> {
                    t1.setOdTypCd("20");
                    t1.setOrglProcSeq(t1.getProcSeq());
                    t1.setProcSeq(t2 + 1);
                    t1.setNewOrder(true);
                    return t1;
                });

        Function<OmOdDtl, Mono<OmOdDtl>> getOrderData = req -> omOdDtlRepository.getOmOdDtlByOdNoAndOdSeqAndProcSeq(req.getOdNo(), req.getOdSeq(), req.getProcSeq());

        BiFunction<OmOdDtl, String, OmOdDtl> combineCombinator = (t1, t2) -> {
            t1.setClmNo(t2);
            return t1;
        };

        return Flux.combineLatest(Flux.fromIterable(reqList)
                        .flatMap(getOrderData)
                        .log("getOd")
                        .flatMap(setCancelData)
                        .log("setProcSeq"),
                omOdDtlRepository.getClmNo(),
                combineCombinator)
                .log()
                .flatMap(omOdDtlRepository::save)
                .log()
                .flatMap(cancel -> Mono.just(cancel)
                        .zipWith(omOdDtlRepository.setFixCnclQtyFor(cancel.getOdQty(), cancel.getOdNo(), cancel.getOdSeq(), cancel.getOrglProcSeq())
                                , (t1, t2) -> t1
                        )
                )
                .flatMap(cancel -> Mono.just(cancel)
                    .zipWith(procCancelFvr(cancel).collectList()
                    , (t1, t2) -> {
                        t1.setOdFvrList(t2);
                        return t1;
                    })
                )
                ;
    }

    private Flux<OmOdFvrDtl> procCancelFvr(OmOdDtl cancel) {
        return omOdFvrDtlRepository.findByOdNoAndOdSeq(cancel.getOdNo(), cancel.getOdSeq())
                .flatMap(fvr -> Mono.just(fvr)
                        .zipWith(omOdFvrDtlRepository.getOdFvrNo()
                                , (t1, t2) -> {
                                    t1.setClmNo(cancel.getClmNo());
                                    t1.setProcSeq(cancel.getProcSeq());
                                    t1.setOrglOdFvrNo(t1.getOdFvrNo());
                                    t1.setOdFvrDvsCd("20");
                                    t1.setOdFvrNo(t2);
                                    t1.setNewOrder(true);
                                    return t1;
                                })
                )
                .flatMap(omOdFvrDtlRepository::save)
                .flatMap(cancelFvr -> Mono.just(cancelFvr)
                        .zipWith(omOdFvrDtlRepository.setFixCnclQtyFor(cancelFvr.getAplyQty(), cancelFvr.getOrglOdFvrNo())
                                , (t1, t2) -> t1)
                )
                ;
    }
}
