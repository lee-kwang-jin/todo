package com.example.todo.config.handler;

import org.springframework.core.Ordered;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import org.springframework.data.r2dbc.mapping.event.BeforeSaveCallback;

public interface RepositoryBeforeSaveCallBackService<T> extends BeforeConvertCallback<T>, BeforeSaveCallback<T> {

}
