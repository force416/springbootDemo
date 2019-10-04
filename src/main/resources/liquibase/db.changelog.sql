--liquibase formatted sql
--changeset version:v1

create table if not exists chats (
    id INTEGER NOT NULL primary key,
    first_name varchar(255),
    last_name varchar(255),
    username varchar(255),
    create_time TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
--rollback drop table chats;

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
--rollback drop table tasks;

create table if not exists alerts (
    id SERIAL NOT NULL primary key,
    chat_id INTEGER NOT NULL REFERENCES chats(id),
    content varchar(255) NOT NULL,
    alert_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_time TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
--rollback drop table alerts;

carete table if not exists boards (
    id SERIAL NOT NULL primary key,
    name varchar(50) NOT NULL,
);
--rollback drop table boards;

create table if not exists chats_boards (
    id SERIAL NOT NULL primary key,
    chat_id INTEGER NOT NULL REFERENCES chats(id),
    board_id INTEGER NOT NULL REFERENCES boards(id),
    like_limit INTEGER NOT NULL DEFAULT 0,
    last_notify_post_id bigint NOT NULL DEFAULT 0
);
--rollback drop table chats_boards;