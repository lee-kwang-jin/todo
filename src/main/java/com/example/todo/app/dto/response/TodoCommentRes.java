package com.example.todo.app.dto.response;

import com.example.todo.app.table.TdComInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class TodoCommentRes {
    private Integer tdComId;
    private Integer upTdComId;
    private Integer tdId;
    private String tdComCont;
    List<TodoCommentRes> subTdComList;

    public TodoCommentRes (TdComInfo tdComInfo) {
        this.tdComId = tdComInfo.getTdComId();
        this.upTdComId = tdComInfo.getUpTdComId();
        this.tdId = tdComInfo.getTdId();
        this.tdComCont = tdComInfo.getTdComCont();
    }
    public TodoCommentRes(TdComInfo main, List<TdComInfo> subTdComList) {
        this.tdComId = main.getTdComId();
        this.upTdComId = main.getUpTdComId();
        this.tdId = main.getTdId();
        this.tdComCont = main.getTdComCont();
        this.subTdComList = subTdComList.stream().map(TodoCommentRes::new).collect(Collectors.toList());
    }

    public TodoCommentRes(TodoCommentRes main, List<TodoCommentRes> subTdComList) {
        this.tdComId = main.getTdComId();
        this.upTdComId = main.getUpTdComId();
        this.tdId = main.getTdId();
        this.tdComCont = main.getTdComCont();
        this.subTdComList = subTdComList;
    }
}
