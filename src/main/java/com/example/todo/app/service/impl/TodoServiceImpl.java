package com.example.todo.app.service.impl;

import com.example.todo.app.dto.request.TodoSaveReq;
import com.example.todo.app.dto.response.TodoCommentRes;
import com.example.todo.app.dto.response.TodoInfoRes;
import com.example.todo.app.dto.response.TodoListRes;
import com.example.todo.app.repository.TodoCommentInfoRepository;
import com.example.todo.app.repository.TodoInfoRepository;
import com.example.todo.app.service.TodoService;
import com.example.todo.app.table.TdComInfo;
import com.example.todo.app.table.TdInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    TodoInfoRepository todoInfoRepository;
    @Autowired
    TodoCommentInfoRepository todoCommentInfoRepository;
    @Autowired
    DatabaseClient databaseClient;
    @Autowired
    R2dbcEntityTemplate r2dbcEntityTemplate;
    @Autowired
    TransactionalOperator transactionalOperator;


    public Function<TodoCommentRes, Mono<TodoCommentRes>> getComment() {
        return tdComInfo -> Mono.zip(
                Mono.just(tdComInfo),
                todoCommentInfoRepository.findByTdIdAndUpTdComId(tdComInfo.getTdId(), tdComInfo.getTdComId())
                        .map(TodoCommentRes::new)
                        .collectList(),
                TodoCommentRes::new)
                .log()
                .flatMap(data -> {
                    Flux<TodoCommentRes> comments = Mono.just(data.getSubTdComList())
                            .flatMapMany(ddd -> Flux.fromStream(ddd.stream()))
                            .flatMap(getComment())
                            .log("rerun");

                    return Mono.just(data).zipWith(comments.collectList(), TodoCommentRes::new);
                });
    }

    public Flux<TodoListRes> getTodoList() {
        return todoInfoRepository.findAll()
                .flatMap(tdInfo -> {
                    Flux<TodoCommentRes> mainTdComInfos = todoCommentInfoRepository.findByTdIdAndUpTdComId(tdInfo.getTdId(), 0)
                            .log("second.query")
                            .map(TodoCommentRes::new)
                            .flatMap(getComment());
                    return Mono.just(tdInfo)
                            .zipWith(
                                    mainTdComInfos.collectList(),
                                    TodoListRes::new)
                            .log("test");
                })
                .log("test1111");
    }

    @Override
    public Flux<TodoInfoRes> getTodoListFromDataClient() {
        return databaseClient
                .sql("select a.td_id, a.td_cont, b.td_com_id, b.up_td_com_id, b.td_com_cont, a.reg_id, a.reg_dtm, a.mod_id, a.mod_dtm " +
                        "from td_info as a left outer join td_com_info as b on (a.td_id = b.td_id)")
                .fetch()
                .all()
                .log("test3")
                .flatMap(todo -> {
                    TodoInfoRes res = new TodoInfoRes();
                    res.setTdId(Integer.parseInt(todo.get("td_id").toString()));
                    return Mono.just(res);
                })
                .log("test4")
                ;
    }

    public Flux<TodoInfoRes> getTodoListFromR2dbcTemplate() {

        return null;
    }

//    @Transactional
    public Mono<TdComInfo> saveTodo(TodoSaveReq req) {
        return Mono.just(req)
                .flatMap(todo -> Mono.just(todo)
                            .subscribeOn(Schedulers.boundedElastic())
                            .map(item -> {
                                TdInfo tdInfo = new TdInfo();
                                BeanUtils.copyProperties(todo, tdInfo);
                                return tdInfo;
                            })
                            .flatMap(todoInfoRepository::save)
                            .flatMap(saveItem -> Flux.fromStream(todo.getTdComInfoList().stream())
                                    .map(item -> {
                                        TdComInfo tdComInfo = new TdComInfo();
                                        BeanUtils.copyProperties(item, tdComInfo);
                                        tdComInfo.setTdId(saveItem.getTdId());
                                        return tdComInfo;
                                    })
                                    .flatMap(todoCommentInfoRepository::save).collectList())
                )
                .as(transactionalOperator::transactional)
                .log()
                .flatMap(data -> Mono.just(new TdComInfo()));
    }
}
