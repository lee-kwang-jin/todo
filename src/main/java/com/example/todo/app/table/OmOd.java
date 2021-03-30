package com.example.todo.app.table;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

@Table
@Data
public class OmOd implements Persistable<String> {

//    public OmOd(OrderSaveReq req) {
//        this.odNo = req.getOdNo();
//        this.mbNo = req.getMbNo();
//        this.odrNm = req.getOdrNm();
//        this.orglOdNo = req.getOrglOdNo();
//        this.odCmptDttm = req.getOdCmptDttm();
//        this.regDttm = req.getRegDttm();
//        this.modDttm = req.getModDttm();
//    }

    @Id
    private String odNo;
    private String mbNo;
    private String odrNm;
    private String orglOdNo;
    private LocalDateTime odCmptDttm;
    private LocalDateTime regDttm;
    private LocalDateTime modDttm;

    @Transient
    private List<OmOdDtl> odDtlList;

    @Transient
    private boolean newOrder;

    @Override
    public String getId() {
        return odNo;
    }

    @Override
    public boolean isNew() {
        return newOrder;
    }
}
