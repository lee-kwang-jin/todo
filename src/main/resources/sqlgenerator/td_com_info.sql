create table td_com_info
(
    td_com_id    serial                   not null
        constraint td_com_info_pk
            primary key,
    up_td_com_id integer                  not null,
    td_id        integer                  not null,
    td_com_cont  varchar                  not null,
    reg_id       varchar                  not null,
    reg_dtm      timestamp with time zone not null,
    mod_id       varchar                  not null,
    mod_dtm      timestamp with time zone not null
);

alter table td_com_info
    owner to postgres;


