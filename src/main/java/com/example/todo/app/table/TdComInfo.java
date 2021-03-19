package com.example.todo.app.table;

import com.example.todo.app.common.domain.CommonDomain;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table
@Data
public class TdComInfo extends CommonDomain {
    @Id
    private Integer tdComId;
    private Integer upTdComId;
    private Integer tdId;
    private String tdComCont;
}
