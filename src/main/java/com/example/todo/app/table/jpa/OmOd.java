package com.example.todo.app.table.jpa;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
public class OmOd {
    @Id
    @Column(name= "od_no")
    private String odNo;
    private String mbNo;
    private String odrNm;
    private String orglOdNo;

    @OneToMany(mappedBy= "omOd")
    private List<OmOdDtl> omOdDtlList;
}
