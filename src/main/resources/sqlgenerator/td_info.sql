create table td_info
(
    td_id   serial                   not null
        constraint td_info_pk
            primary key,
    td_cont varchar                  not null,
    reg_id  varchar                  not null,
    reg_dtm timestamp with time zone not null,
    mod_id  varchar                  not null,
    mod_dtm timestamp with time zone not null
);

alter table td_info
    owner to postgres;


