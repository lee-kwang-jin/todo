package com.example.todo.config.handler;

import com.example.todo.annotation.Masked;
import com.example.todo.app.common.domain.CommonDomain;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.annotation.Id;
import org.springframework.data.auditing.ReactiveIsNewAwareAuditingHandler;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class RepositoryBeforeSaveCallBackServiceImpl<T> implements RepositoryBeforeSaveCallBackService<T> {

    @Override
    public Publisher<T> onBeforeConvert(T entity, SqlIdentifier table) {

//        Class currentClass = entity.getClass();
//
//        Class superClass = currentClass.getSuperclass();
//
//        if(Objects.nonNull(superClass) && CommonDomain.class.equals(superClass)) {
//            Field[] fields = superClass.getDeclaredFields();
//
//            try {
//                for(Field field : fields) {
//                    field.setAccessible(true);
//
//                    if(field.getType().equals(DomainType.TIME.getType())) {
//                        field.set(entity, LocalDateTime.now());
//                        continue;
//                    }
//
//                    // 차후 session or authenticate의 principle로 변경 예정.
//                    if(field.getType().equals(DomainType.ID.getType())) {
//                        field.set(entity, "system");
//                        continue;
//                    }
//                }
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }

        Mono<T> mono = Mono.just(entity)
                .map(s -> {
                    Class currentClass = s.getClass();

                    List<Field> fields = Arrays.stream(currentClass.getDeclaredFields())
                            .filter(f -> f.isAnnotationPresent(Id.class))
                            .collect(Collectors.toList());

                    for(Field field : fields) {
                        field.setAccessible(true);
                        try {
                            Object obj = field.get(entity);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }

                    Class superClass = currentClass.getSuperclass();

                    if(Objects.nonNull(superClass) && CommonDomain.class.equals(superClass)) {
                        fields.addAll(Arrays.asList(superClass.getDeclaredFields()));

                        try {
                            for(Field field : fields) {
                                field.setAccessible(true);

                                if(field.getType().equals(DomainType.TIME.getType())) {
                                    field.set(s, LocalDateTime.now());
                                    continue;
                                }

                                // 차후 session or authenticate의 principle로 변경 예정.
                                if(field.getType().equals(DomainType.ID.getType())) {
                                    field.set(entity, "system");
                                    continue;
                                }
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }

                    return s;
                })
                ;

        return mono;
    }

    @Override
    public int getOrder() {
        return 1;
    }

//    @Override
//    public Publisher<T> onBeforeSave(T entity, OutboundRow row, SqlIdentifier table) {
//        log.debug("row => :", row);
//        return null;
//    }

    enum DomainType {
        TIME(LocalDateTime.class),
        ID(String.class),
        ;

        DomainType(Class clazz) {
            this.type = clazz;
        }

        private Class type;

        public Class getType() {
            return this.type;
        }
    }
}
