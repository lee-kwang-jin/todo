package com.example.todo.app.projection;

import org.springframework.beans.factory.annotation.Value;

/**
 * Open Projection
 * 접근자 메소드로 새로운 값을 계산하기 위해 사용.
 */
public interface TdInfoOpenProjection {
    Integer getTdId();
    String getTdCont();

    // target변수로 접근하여 값을 가져와서 사용
    // @Value 안에 있는 표현식이 너무 복잡해선 안 된다
    @Value("#{target.tdId + ' ' + target.tdCont}")
    String getJoinTd();

    // Logical
    default String getJoinTd2() {
        return getTdId() + "," + getTdCont();
    }
//
//    @Value("#{@tdInfoBean.getJoinTd3(target)}")
//    String getJoinTd3();

//    @Value("#{args[0] + ':' + target.tdId}")
//    String getJoinTd4(String prefix);
}
