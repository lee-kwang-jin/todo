package com.example.todo.app.dto.request;

import com.example.todo.app.dto.response.TodoCommentRes;
import lombok.Data;

import java.util.List;

@Data
public class TodoSaveReq {
    private String tdCont;
    private List<TodoCommentSaveReq> tdComInfoList;
}
