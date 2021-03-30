package com.example.todo.app.controller;

import com.example.todo.app.domain.OmOdDtlKeys;
import com.example.todo.app.service.OmOdDtlService;
import com.example.todo.app.service.OrderService;
import com.example.todo.app.table.OmOd;
import com.example.todo.app.table.OmOdDtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    OmOdDtlService omOdDtlService;

    @PostMapping("/save")
    public Mono<OmOd> saveOrder(@RequestBody OmOd req) {
        return orderService.saveOrder(req);
    }

    @PostMapping("/dtl/save")
    public Mono<OmOdDtl> saveOmOdDtl(@RequestBody OmOdDtl omOdDtl) {
        return omOdDtlService.saveOmOdDtl(omOdDtl);
    }

    @GetMapping("/dtl/list")
    public Flux<OmOdDtl> getOmOdDtlList() {
        return omOdDtlService.getOmOdDtlList();
    }

    @GetMapping("/dtl/item")
    public Mono<OmOdDtl> getOmOdDtl(OmOdDtlKeys keys) {
        return omOdDtlService.getOmOdDtl(keys);
    }
}
