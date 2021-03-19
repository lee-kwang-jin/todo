package com.example.todo.app.common.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommonDomain {
    private String regId;
    private LocalDateTime regDtm;
    private String modId;
    private LocalDateTime modDtm;
}
