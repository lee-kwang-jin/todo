package com.example.todo.app.common.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommonDomain {

    private String regId;
    private LocalDateTime regDtime;
    private String modId;
    private LocalDateTime modDtime;

}
