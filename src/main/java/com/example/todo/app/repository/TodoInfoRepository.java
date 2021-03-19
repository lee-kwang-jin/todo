package com.example.todo.app.repository;

import com.example.todo.app.table.TdComInfo;
import com.example.todo.app.table.TdInfo;
import com.example.todo.config.handler.RepositoryBeforeSaveCallBackService;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TodoInfoRepository extends ReactiveCrudRepository<TdInfo, Integer>, RepositoryBeforeSaveCallBackService<TdInfo> {
}
