package com.example.todo.app.projection;

/**
 * Close Projection - 접근자 메소드가 모두 타겟 집계 타입의 프로퍼티와 일치하는 프로젝션
 * 프로젝션 프록시에 필요한 속성을 전부 알 수 있기 때문에 쿼리 실행을 최적화할 수 있다
 */
public interface TdInfoCloseProjection {
    Integer getTdId();
    String getTdCont();
}
