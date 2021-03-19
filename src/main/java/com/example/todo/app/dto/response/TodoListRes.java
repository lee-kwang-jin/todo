package com.example.todo.app.dto.response;

import com.example.todo.app.table.TdComInfo;
import com.example.todo.app.table.TdInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TodoListRes {

    private Integer tdId;
    private String tdCont;
    private List<TodoCommentRes> tdComInfoList;

    public TodoListRes(TdInfo tdInfo, List<TodoCommentRes> tdComInfos) {
        this.tdId = tdInfo.getTdId();
        this.tdCont = tdInfo.getTdCont();
        this.tdComInfoList = tdComInfos;
    }
}
