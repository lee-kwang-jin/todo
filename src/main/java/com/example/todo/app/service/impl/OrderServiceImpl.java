package com.example.todo.app.service.impl;

import com.example.todo.app.dto.response.OrderFavorInfo;
import com.example.todo.app.repository.OmOdDtlRepository;
import com.example.todo.app.repository.OmOdFvrDtlRepository;
import com.example.todo.app.repository.OmOdRepository;
import com.example.todo.app.service.OrderService;
import com.example.todo.app.table.OmOd;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
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
    @Autowired
    DatabaseClient databaseClient;

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

    @Override
    public Flux<OrderFavorInfo> getOrderByFavor() {
        return databaseClient.sql(
                "select *\n" +
                " from om_od_dtl as a\n" +
                "    , om_od_fvr_dtl as b\n" +
                "where a.od_no = :odNo\n" +
                "  and a.od_no = b.od_no\n" +
                "  and a.od_seq = b.od_seq\n" +
                "  and a.proc_seq = b.proc_seq\n" +
                "  and coalesce(b.aply_qty, 0) - coalesce(b.cncl_qty, 0) > 0")
                .bind("odNo", "2021033110000045")
                .fetch()
                .all()
                .map(row -> getReturnValue(new OrderFavorInfo(), row));
    }

    private <T> T getReturnValue(T t, Map<String, Object> row) {
        Field[] fields = t.getClass().getDeclaredFields();
        for(Field field : fields) {
            field.setAccessible(true);
            try {
                if( !field.isAnnotationPresent(Transient.class) ) {
                    field.set(t, row.get(camelToSnake(field.getName())));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return t;
    }

    private String camelToSnake(String camelStr) {
        // Regular Expression
        String regex = "([a-z])([A-Z]+)";

        // Replacement string
        String replacement = "$1_$2";

        // Replace the given regex
        // with replacement string
        // and convert it to lower case.
        // return string
        return camelStr.replaceAll(regex, replacement).toLowerCase();
    }
}
