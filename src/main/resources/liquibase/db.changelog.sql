--liquibase formatted sql

--changeset version:v1
create table customer (
    id int primary key,
    first_name varchar(255),
    last_name varchar(255)
);
--rollback drop table customer;