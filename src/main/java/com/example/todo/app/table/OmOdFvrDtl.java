package com.example.todo.app.table;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table
@Data
public class OmOdFvrDtl implements Persistable<String> {
    @Id
    private String odFvrNo;
    private String odNo;
    private Integer odSeq;
    private Integer procSeq;
    private String clmNo;
    private String orglOdFvrNo;
    private String odFvrDvsCd;
    private String dcTnnoCd;
    private Integer aplyQty;
    private Integer cnclQty;
    private Integer fvrAmt;
    private String prNo;
    private String prNm;
    private String cpnNo;
    private String cpnNm;
    private LocalDateTime regDttm;
    private LocalDateTime modDttm;

    @Transient
    private boolean newOrder;

    @Override
    public String getId() {
        return odFvrNo;
    }

    @Override
    public boolean isNew() {
        return newOrder;
    }
}
