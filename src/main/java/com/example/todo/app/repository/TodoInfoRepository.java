package com.example.todo.app.repository;

import com.example.todo.app.dto.response.TodoProjectionDto;
import com.example.todo.app.projection.TdInfoCloseProjection;
import com.example.todo.app.projection.TdInfoOpenProjection;
import com.example.todo.app.table.TdInfo;
import com.example.todo.config.handler.RepositoryBeforeSaveCallBackService;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TodoInfoRepository extends ReactiveCrudRepository<TdInfo, Integer>, RepositoryBeforeSaveCallBackService<TdInfo> {
//    Flux<TdInfoOpenProjection> findAllBy();
//
//    Flux<TdInfoOpenProjection> findAllByTdId(Integer tdId);
//
//    Flux<TdInfoCloseProjection> readAllBy();
//
//    Flux<TdInfoCloseProjection> readAllByTdId(Integer tdId);
//
//    Flux<TodoProjectionDto> getAllBy();
//
//    /**
//     * DTO projection 조회문의 조건절이 들어갔을 경우 DTO 클래스 내 조건 field가 선언이 되어있지 않으면 build시 오류 발생.
//     * predicate(조건) 생성시 DTO 클래스의 field로 생성되고 있어 조건문에 해당하는 field가 없으면 오류 발생.
//     * @param tdCont
//     * @return
//     */
//    Flux<TodoProjectionDto> getAllByTdCont(String tdCont);

    <T> Flux<T> searchAllBy(Class<T> type);

    <T> Flux<T> searchAllByTdId(Integer tdId, Class<T> type);
}
