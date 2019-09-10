--liquibase formatted sql

--changeset version:v1
create table customer (
    id SERIAL NOT NULL primary key,
    first_name varchar(255),
    last_name varchar(255)
);
--rollback drop table customer;