package com.example.todo.config.converter;

import com.example.todo.app.domain.OmOdDtlDomain;
import com.example.todo.app.table.OmOdDtl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.r2dbc.core.Parameter;

@WritingConverter
@Slf4j
public class OmOdDtlWriteConverter implements Converter<OmOdDtl, OutboundRow> {

    @Override
    public OutboundRow convert(OmOdDtl source) {
        OutboundRow row = new OutboundRow();

        row.put("od_no", Parameter.from(source.getOdNo()));
        row.put("od_seq", Parameter.from(source.getOdSeq()));
        row.put("proc_seq", Parameter.from(source.getProcSeq()));
        row.put("od_typ_cd", Parameter.from(source.getOdTypCd()));

        return row;
    }
}
