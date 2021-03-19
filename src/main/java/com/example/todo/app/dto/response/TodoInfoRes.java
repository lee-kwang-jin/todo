package com.example.todo.app.dto.response;

import com.example.todo.app.table.TdComInfo;
import com.example.todo.app.table.TdInfo;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoInfoRes {
    private Integer tdId;
    private String tdCont;
    private Integer tdComId;
    private Integer upTdComId;
    private String tdComCont;
    private String regId;
    private LocalDateTime regDtm;
    private String modId;
    private LocalDateTime modDtm;
}
