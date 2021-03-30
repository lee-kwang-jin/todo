package com.example.todo.app.dto.request;

import com.example.todo.app.table.OmOdDtl;
import com.example.todo.app.table.OmOdFvrDtl;
import lombok.Data;

import java.util.List;

@Data
public class OrderDetailSaveReq extends OmOdDtl {
    List<OmOdFvrDtl> orderFavorDetailList;
}
