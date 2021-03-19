package com.example.todo.app.table;

import com.example.todo.app.common.domain.CommonDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TdInfo extends CommonDomain {
    @Id
    private Integer tdId;
    private String tdCont;

    @With
    @Transient
    private List<TdComInfo> tdComInfoList;

    public TdInfo(TdInfo tdInfo, List<TdComInfo> tdComInfoList) {
        this.tdId = tdInfo.getTdId();
        this.tdCont = tdInfo.getTdCont();
        this.tdComInfoList = tdComInfoList;
    }
}
