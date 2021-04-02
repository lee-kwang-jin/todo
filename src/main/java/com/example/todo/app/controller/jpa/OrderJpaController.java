package com.example.todo.app.controller.jpa;

import com.example.todo.app.projection.jpa.OmOdDtlView;
import com.example.todo.app.repository.jpa.OmOdDtlJpaRepository;
import com.example.todo.app.repository.jpa.OmOdJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/jpa/order")
public class OrderJpaController {

    @Autowired
    OmOdDtlJpaRepository omOdDtlJpaRepository;

    @GetMapping("/dtl/list")
    public Flux<OmOdDtlView> getOmOdList() {
        return Flux.fromIterable(omOdDtlJpaRepository.findAllBy());
    }
}
