--liquibase formatted sql
--changeset version:v1

create table if not exists chats (
    id INTEGER NOT NULL primary key,
    first_name varchar(255),
    last_name varchar(255),
    username varchar(255),
    create_time TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
--rollback drop table customer;

create table if not exists tasks (
    id SERIAL NOT NULL primary key,
    hash varchar(30) NOT NULL UNIQUE,
    chat_id INTEGER NOT NULL REFERENCES chats(id),
    content varchar(255) NOT NULL,
    status varchar(30) NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT false,
    create_time TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
--rollback drop table customer;