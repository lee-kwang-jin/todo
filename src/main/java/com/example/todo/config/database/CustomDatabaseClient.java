package com.example.todo.config.database;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.dialect.PostgresDialect;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.r2dbc.connection.SingleConnectionFactory;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.reactive.TransactionalOperator;

@Configuration
public class CustomDatabaseClient extends AbstractR2dbcConfiguration {

    @Override
    @Bean
    public PostgresqlConnectionFactory connectionFactory() {

        return new PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration.builder()
                .host("127.0.0.1")
                .username("postgres")
                .password("1qaz2wsx")
                .build());
    }

    @Bean
    public ReactiveTransactionManager reactiveTransactionManager(@Qualifier("connectionFactory") ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }

    @Bean
    public TransactionalOperator transactionalOperator(@Qualifier("reactiveTransactionManager") ReactiveTransactionManager reactiveTransactionManager) {
        return TransactionalOperator.create(reactiveTransactionManager);
    }
}
