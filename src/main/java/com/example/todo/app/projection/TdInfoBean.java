package com.example.todo.app.projection;

import com.example.todo.app.table.TdInfo;
import org.springframework.stereotype.Component;

@Component("tdInfoBean")
public class TdInfoBean {
    String getJoinTd3(TdInfo tdInfo) {
        return tdInfo.getTdId() + " : " + tdInfo.getTdCont();
    }
}
