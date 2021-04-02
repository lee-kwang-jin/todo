package com.example.todo.app.table.jpa;

import com.example.todo.app.table.jpa.key.OmOdDtlKeys;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@IdClass(OmOdDtlKeys.class)
@Data
public class OmOdDtl {
    @Id
    @Column(name= "od_no")
    private String odNo;
    @Id
    private Integer odSeq;
    @Id
    private Integer procSeq;
    private String clmNo;
    private String odTypCd;
    private String odPrgsStepCd;
    private String mbNo;
    private Integer odQty;

    @ManyToOne
    @JoinColumn(name= "od_no", nullable = false, insertable = false, updatable = false)
    private OmOd omOd;
}
