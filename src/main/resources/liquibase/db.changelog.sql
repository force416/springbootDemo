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

create table if not exists boards (
    id SERIAL NOT NULL primary key,
    name varchar(50) NOT NULL UNIQUE
);
INSERT INTO boards (name) VALUES ('gossiping');
INSERT INTO boards (name) VALUES ('c_chat');
INSERT INTO boards (name) VALUES ('baseball');
INSERT INTO boards (name) VALUES ('lol');
INSERT INTO boards (name) VALUES ('movie');
INSERT INTO boards (name) VALUES ('nba');
INSERT INTO boards (name) VALUES ('lifeismoney');
INSERT INTO boards (name) VALUES ('stock');
INSERT INTO boards (name) VALUES ('womentalk');
INSERT INTO boards (name) VALUES ('sex');
INSERT INTO boards (name) VALUES ('taiwandrama');
INSERT INTO boards (name) VALUES ('hatepolitics');
INSERT INTO boards (name) VALUES ('mobilecomm');
INSERT INTO boards (name) VALUES ('tech_job');
INSERT INTO boards (name) VALUES ('car');
INSERT INTO boards (name) VALUES ('e-shopping');
INSERT INTO boards (name) VALUES ('kr_entertain');
INSERT INTO boards (name) VALUES ('marriage');
INSERT INTO boards (name) VALUES ('beauty');
INSERT INTO boards (name) VALUES ('babymother');
INSERT INTO boards (name) VALUES ('boy-girl');
INSERT INTO boards (name) VALUES ('pokemongo');
INSERT INTO boards (name) VALUES ('japan_travel');
INSERT INTO boards (name) VALUES ('ios');
INSERT INTO boards (name) VALUES ('alltogether');
INSERT INTO boards (name) VALUES ('home-sale');
INSERT INTO boards (name) VALUES ('hearthstone');
INSERT INTO boards (name) VALUES ('pc_shopping');
INSERT INTO boards (name) VALUES ('koreastar');
INSERT INTO boards (name) VALUES ('joke');
INSERT INTO boards (name) VALUES ('creditcard');
INSERT INTO boards (name) VALUES ('koreadrama');
INSERT INTO boards (name) VALUES ('makeup');
INSERT INTO boards (name) VALUES ('tos');
INSERT INTO boards (name) VALUES ('sportlottery');
INSERT INTO boards (name) VALUES ('marvel');
INSERT INTO boards (name) VALUES ('tainan');
INSERT INTO boards (name) VALUES ('beautysalon');
INSERT INTO boards (name) VALUES ('kaohsiung');
INSERT INTO boards (name) VALUES ('taichungbun');
INSERT INTO boards (name) VALUES ('mh');
INSERT INTO boards (name) VALUES ('twice');
INSERT INTO boards (name) VALUES ('elephants');
INSERT INTO boards (name) VALUES ('steam');
INSERT INTO boards (name) VALUES ('japanavgirls');
INSERT INTO boards (name) VALUES ('golden-award');
INSERT INTO boards (name) VALUES ('playstation');
INSERT INTO boards (name) VALUES ('tw_entertain');
INSERT INTO boards (name) VALUES ('stupidclown');
INSERT INTO boards (name) VALUES ('nswitch');
INSERT INTO boards (name) VALUES ('cfantasy');
INSERT INTO boards (name) VALUES ('japandrama');
INSERT INTO boards (name) VALUES ('wow');
INSERT INTO boards (name) VALUES ('biker');
INSERT INTO boards (name) VALUES ('hardwaresale');
INSERT INTO boards (name) VALUES ('fate_go');
INSERT INTO boards (name) VALUES ('cvs');
INSERT INTO boards (name) VALUES ('mobilesales');
INSERT INTO boards (name) VALUES ('one_piece');
INSERT INTO boards (name) VALUES ('mlb');
INSERT INTO boards (name) VALUES ('tennis');
INSERT INTO boards (name) VALUES ('buytogether');
INSERT INTO boards (name) VALUES ('isayama');
INSERT INTO boards (name) VALUES ('publicservan');
INSERT INTO boards (name) VALUES ('hsinchu');
INSERT INTO boards (name) VALUES ('nba_film');
INSERT INTO boards (name) VALUES ('monkeys');
INSERT INTO boards (name) VALUES ('lakers');
INSERT INTO boards (name) VALUES ('aviation');
INSERT INTO boards (name) VALUES ('musclebeach');
INSERT INTO boards (name) VALUES ('yuanchuang');
INSERT INTO boards (name) VALUES ('koreanpop');
INSERT INTO boards (name) VALUES ('typemoon');
INSERT INTO boards (name) VALUES ('azurlane');
INSERT INTO boards (name) VALUES ('salary');
INSERT INTO boards (name) VALUES ('macshop');
INSERT INTO boards (name) VALUES ('easeries');
INSERT INTO boards (name) VALUES ('examination');
INSERT INTO boards (name) VALUES ('getmarry');
INSERT INTO boards (name) VALUES ('food');
INSERT INTO boards (name) VALUES ('headphone');
INSERT INTO boards (name) VALUES ('china-drama');
INSERT INTO boards (name) VALUES ('ac_in');
INSERT INTO boards (name) VALUES ('soft_job');
INSERT INTO boards (name) VALUES ('medstudent');
INSERT INTO boards (name) VALUES ('forsale');
INSERT INTO boards (name) VALUES ('gamesale');
INSERT INTO boards (name) VALUES ('carshop');
INSERT INTO boards (name) VALUES ('watch');
INSERT INTO boards (name) VALUES ('e-appliance');
INSERT INTO boards (name) VALUES ('ty_research');
INSERT INTO boards (name) VALUES ('pcredive');
INSERT INTO boards (name) VALUES ('mobilepay');
INSERT INTO boards (name) VALUES ('helpbuy');
INSERT INTO boards (name) VALUES ('part-time');
INSERT INTO boards (name) VALUES ('pathofexile');
INSERT INTO boards (name) VALUES ('military');
INSERT INTO boards (name) VALUES ('cookclub');
INSERT INTO boards (name) VALUES ('give');
INSERT INTO boards (name) VALUES ('wanted');
INSERT INTO boards (name) VALUES ('finance');
INSERT INTO boards (name) VALUES ('brand');
INSERT INTO boards (name) VALUES ('dslr');
INSERT INTO boards (name) VALUES ('gay');
INSERT INTO boards (name) VALUES ('bb-love');
INSERT INTO boards (name) VALUES ('tabletennis');
INSERT INTO boards (name) VALUES ('izone');
INSERT INTO boards (name) VALUES ('cat');
INSERT INTO boards (name) VALUES ('lesbian');
INSERT INTO boards (name) VALUES ('korea_travel');
INSERT INTO boards (name) VALUES ('basketballtw');
INSERT INTO boards (name) VALUES ('dc_sale');
INSERT INTO boards (name) VALUES ('babyproducts');
INSERT INTO boards (name) VALUES ('fitness');
INSERT INTO boards (name) VALUES ('kancolle');
INSERT INTO boards (name) VALUES ('zastrology');
INSERT INTO boards (name) VALUES ('nogizaka46');
INSERT INTO boards (name) VALUES ('feminine_sex');
INSERT INTO boards (name) VALUES ('gov_owned');
INSERT INTO boards (name) VALUES ('chungli');
INSERT INTO boards (name) VALUES ('hypermall');
INSERT INTO boards (name) VALUES ('mayday');
INSERT INTO boards (name) VALUES ('akb48');
INSERT INTO boards (name) VALUES ('superjunior');
INSERT INTO boards (name) VALUES ('drama-ticket');
INSERT INTO boards (name) VALUES ('nb-shopping');
INSERT INTO boards (name) VALUES ('facelift');
INSERT INTO boards (name) VALUES ('taoyuan');
--rollback drop table boards;

create table if not exists chats_boards (
    id SERIAL NOT NULL primary key,
    chat_id INTEGER NOT NULL REFERENCES chats(id),
    board_id INTEGER NOT NULL REFERENCES boards(id),
    like_limit INTEGER NOT NULL DEFAULT 0,
    last_notify_post_id bigint NOT NULL DEFAULT 0,
    UNIQUE (chat_id, board_id)
);
--rollback drop table chats_boards;