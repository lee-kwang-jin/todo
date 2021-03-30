package com.example.todo.app.table;

import com.example.todo.app.domain.OmOdDtlKeys;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

@Table
@Data
public class OmOdDtl implements Persistable<String> {
    @Id
    private String odNo;
    private Integer odSeq;
    private Integer procSeq;
    private String clmNo;
    private String odTypCd;
    private String odPrgsStepCd;
    private String mbNo;
    private Integer odQty;
    private Integer cnclQty;
    private Integer rtngQty;
    private Integer xchgQty;
    private Integer slPrc;
    private Integer dcAmt;
    private String pdNo;
    private String pdNm;
    private String prNo;
    private LocalDateTime odCmptDttm;
    private LocalDateTime purCfrmDttm;
    private LocalDateTime regDttm;
    private LocalDateTime modDttm;
    private Integer orglProcSeq;

    @Transient
    private List<OmOdFvrDtl> odFvrList;

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
