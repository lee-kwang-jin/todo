package com.example.todo.config.handler;

import com.example.todo.app.domain.TodoListDomain;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.data.r2dbc.mapping.event.BeforeSaveCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.r2dbc.core.Parameter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class RepositoryTest implements BeforeSaveCallback<TodoListDomain> {

//    @Autowired
//    TodoListRepository repository;

    @Override
    public Publisher<TodoListDomain> onBeforeSave(TodoListDomain entity, OutboundRow row, SqlIdentifier table) {


//        log.debug("test :::: ", repository.nextval());

        row.put("reg_id", Parameter.from("test1111"));

        return Mono.just(entity);
    }
}
