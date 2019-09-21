--liquibase formatted sql
--changeset version:v1

create table if not exists users (
    id INTEGER NOT NULL primary key,
    first_name varchar(255),
    last_name varchar(255),
    username varchar(255)
);
--rollback drop table customer;

DROP TYPE IF EXISTS item_status;
create type item_status as enum('todo','done');

create table if not exists tasks (
    id SERIAL NOT NULL primary key,
    user_id INTEGER NOT NULL REFERENCES users(id),
    content varchar(255) NOT NULL,
    status item_status NOT NULL,
    deleted BOOLEAN NOT NULL
);
--rollback drop table customer;