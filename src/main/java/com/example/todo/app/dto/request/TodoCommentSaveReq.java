package com.example.todo.app.dto.request;

import lombok.Data;

@Data
public class TodoCommentSaveReq {
    private Integer upTdComId;
    private Integer tdId;
    private String tdComCont;
}
