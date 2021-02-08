package com.example.todo.app.domain;

import com.example.todo.app.common.domain.CommonDomain;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("todo_list")
@Data
public class TodoListDomain extends CommonDomain {
    @Id
    private int todoId;
    private String todoItem;
    private String todoSubjectId;

//    todo_id         serial                   not null,
//    todo_item       varchar                  not null,
//    todo_subject_id varchar                  not null,
//    reg_id          varchar                  not null,
//    reg_dtime       timestamp with time zone not null,
//    mod_id          varchar                  not null,
//    mod_dtime       timestamp with time zone not null
}
