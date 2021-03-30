package com.example.todo.app.service.impl;

import com.example.todo.app.repository.OmOdDtlRepository;
import com.example.todo.app.repository.OmOdFvrDtlRepository;
import com.example.todo.app.repository.OmOdRepository;
import com.example.todo.app.service.OrderService;
import com.example.todo.app.table.OmOd;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OmOdRepository omOdRepository;
    @Autowired
    OmOdDtlRepository omOdDtlRepository;
    @Autowired
    OmOdFvrDtlRepository omOdFvrDtlRepository;

    public Mono<OmOd> saveOrder(OmOd req) {
        // 주문기본, 주문상세, 혜택 저장.
        return omOdRepository.getOdNo()
                .zipWith(Mono.just(req), (odNo, order) -> {
                    order.setOdNo(odNo);
                    order.setNewOrder(true);
                    return order;
                })
                .log("getOdNo")
                .flatMap((order) -> {
                    OmOd od = new OmOd();
                    BeanUtils.copyProperties(order, od);
                    return omOdRepository.save(od);
                })
                .flatMap(order -> {
                    AtomicInteger atomicInteger = new AtomicInteger(1);
                    order.getOdDtlList().stream().forEach(omOdDtl -> {
                        omOdDtl.setOdNo(order.getOdNo());
                        omOdDtl.setOdSeq(atomicInteger.getAndIncrement());
                        omOdDtl.setProcSeq(1);
                        omOdDtl.setNewOrder(true);
                    });

                    return Mono.just(order)
                            .zipWith(Flux.fromIterable(order.getOdDtlList())
                                    .flatMap(omOdDtlRepository::save)
                                    .flatMap(orderDetail -> {
                                        if(Objects.nonNull(orderDetail.getOdFvrList()) && !orderDetail.getOdFvrList().isEmpty()) {
                                            orderDetail.getOdFvrList().stream().forEach(omOdFvrDtl -> {
                                                omOdFvrDtl.setOdNo(orderDetail.getOdNo());
                                                omOdFvrDtl.setOdSeq(orderDetail.getOdSeq());
                                                omOdFvrDtl.setProcSeq(orderDetail.getProcSeq());
                                                omOdFvrDtl.setNewOrder(true);
                                            });
                                        } else {
                                            orderDetail.setOdFvrList(new ArrayList<>());
                                        }

                                        return Mono.just(orderDetail)
                                                .zipWith(Flux.fromIterable(orderDetail.getOdFvrList())
                                                        .zipWith(omOdFvrDtlRepository.getOdFvrNo(), (t1, t2) -> {
                                                            t1.setOdFvrNo(t2);
                                                            return t1;
                                                        })
                                                         .flatMap(omOdFvrDtlRepository::save)
                                                        .collectList(), (t1, t2) -> {
                                                    t1.setOdFvrList(t2);
                                                    return t1;
                                                })
                                                ;
                                    })
                                    .collectList(), (t1, t2) -> {
                                t1.setOdDtlList(t2);
                                return t1;
                            })
                            .log();
                })
                .log("save")
                ;
    }
}
