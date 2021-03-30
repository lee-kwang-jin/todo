package com.example.todo.config.converter;

import com.example.todo.app.table.OmOdDtl;
import com.example.todo.app.table.TdInfo;
import io.r2dbc.spi.Row;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
@Slf4j
public class OmOdDtlReadingConverter implements Converter<Row, OmOdDtl> {

    @Override
    public OmOdDtl convert(Row source) {
        log.debug("test", source);
        return new OmOdDtl();
    }
}
