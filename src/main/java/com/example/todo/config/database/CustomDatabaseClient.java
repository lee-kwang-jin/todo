package com.example.todo.config.database;

import com.example.todo.config.converter.OmOdDtlReadingConverter;
import com.example.todo.config.converter.OmOdDtlWriteConverter;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.reactive.TransactionalOperator;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    protected List<Object> getCustomConverters() {
        List<Object> converterList = new ArrayList<>();
        // converterList.add(new OmOdDtlReadingConverter());
        // converterList.add(new OmOdDtlWriteConverter());
        return converterList;
    }

    @Bean
    public ReactiveTransactionManager reactiveTransactionManager(@Qualifier("connectionFactory") ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }

    @Bean
    public TransactionalOperator transactionalOperator(@Qualifier("reactiveTransactionManager") ReactiveTransactionManager reactiveTransactionManager) {
        return TransactionalOperator.create(reactiveTransactionManager);
    }

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://127.0.0.1:5432")
                .username("postgres")
                .password("1qaz2wsx")
                .build();
    }
}
