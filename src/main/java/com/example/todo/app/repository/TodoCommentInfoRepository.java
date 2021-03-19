package com.example.todo.app.repository;

import com.example.todo.app.table.TdComInfo;
import com.example.todo.config.handler.RepositoryBeforeSaveCallBackService;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface TodoCommentInfoRepository extends ReactiveCrudRepository<TdComInfo, Integer>, RepositoryBeforeSaveCallBackService<TdComInfo> {
    Flux<TdComInfo> findByTdIdAndUpTdComId(Integer tdId, Integer upTdComId);
}
