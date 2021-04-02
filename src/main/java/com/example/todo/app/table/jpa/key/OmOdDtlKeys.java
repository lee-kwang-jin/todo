package com.example.todo.app.table.jpa.key;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OmOdDtlKeys implements Serializable {
    private String odNo;
    private Integer odSeq;
    private Integer procSeq;
}
