package com.example.todo.app.table;

import com.example.todo.app.common.domain.CommonDomain;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

@Table
@Data
public class TdInfo extends CommonDomain {
    @Id
    private Integer tdId;
    private String tdCont;

    @Transient
    private List<TdComInfo> tdComInfoList;
}
