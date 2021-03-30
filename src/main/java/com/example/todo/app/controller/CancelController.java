package com.example.todo.app.controller;

import com.example.todo.app.service.CancelService;
import com.example.todo.app.table.OmOdDtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/cancel")
public class CancelController {

    @Autowired
    CancelService cancelService;

    @PostMapping("/save")
    public Flux<OmOdDtl> orderCancel(@RequestBody List<OmOdDtl> reqList) {
        return cancelService.orderCancel(reqList);
    }
}
