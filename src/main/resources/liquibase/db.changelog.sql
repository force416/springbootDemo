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
INSERT INTO boards (name) VALUES ('Gossiping');
INSERT INTO boards (name) VALUES ('C_Chat');
INSERT INTO boards (name) VALUES ('Baseball');
INSERT INTO boards (name) VALUES ('LoL');
INSERT INTO boards (name) VALUES ('NBA');
INSERT INTO boards (name) VALUES ('movie');
INSERT INTO boards (name) VALUES ('Lifeismoney');
INSERT INTO boards (name) VALUES ('Stock');
INSERT INTO boards (name) VALUES ('sex');
INSERT INTO boards (name) VALUES ('WomenTalk');
INSERT INTO boards (name) VALUES ('HatePolitics');
INSERT INTO boards (name) VALUES ('MobileComm');
INSERT INTO boards (name) VALUES ('TaiwanDrama');
INSERT INTO boards (name) VALUES ('Tech_Job');
INSERT INTO boards (name) VALUES ('car');
INSERT INTO boards (name) VALUES ('e-shopping');
INSERT INTO boards (name) VALUES ('marriage');
INSERT INTO boards (name) VALUES ('Beauty');
INSERT INTO boards (name) VALUES ('BabyMother');
INSERT INTO boards (name) VALUES ('KR_Entertain');
INSERT INTO boards (name) VALUES ('Boy-Girl');
INSERT INTO boards (name) VALUES ('Japan_Travel');
INSERT INTO boards (name) VALUES ('PokemonGO');
INSERT INTO boards (name) VALUES ('iOS');
INSERT INTO boards (name) VALUES ('home-sale');
INSERT INTO boards (name) VALUES ('PC_Shopping');
INSERT INTO boards (name) VALUES ('AllTogether');
INSERT INTO boards (name) VALUES ('KoreaStar');
INSERT INTO boards (name) VALUES ('creditcard');
INSERT INTO boards (name) VALUES ('KoreaDrama');
INSERT INTO boards (name) VALUES ('joke');
INSERT INTO boards (name) VALUES ('MakeUp');
INSERT INTO boards (name) VALUES ('SportLottery');
INSERT INTO boards (name) VALUES ('TWICE');
INSERT INTO boards (name) VALUES ('ToS');
INSERT INTO boards (name) VALUES ('marvel');
INSERT INTO boards (name) VALUES ('TW_Entertain');
INSERT INTO boards (name) VALUES ('Kaohsiung');
INSERT INTO boards (name) VALUES ('BeautySalon');
INSERT INTO boards (name) VALUES ('Tainan');
INSERT INTO boards (name) VALUES ('MH');
INSERT INTO boards (name) VALUES ('Hearthstone');
INSERT INTO boards (name) VALUES ('TaichungBun');
INSERT INTO boards (name) VALUES ('Golden-Award');
INSERT INTO boards (name) VALUES ('Elephants');
INSERT INTO boards (name) VALUES ('japanavgirls');
INSERT INTO boards (name) VALUES ('PlayStation');
INSERT INTO boards (name) VALUES ('Steam');
INSERT INTO boards (name) VALUES ('StupidClown');
INSERT INTO boards (name) VALUES ('NSwitch');
INSERT INTO boards (name) VALUES ('Japandrama');
INSERT INTO boards (name) VALUES ('CFantasy');
INSERT INTO boards (name) VALUES ('BuyTogether');
INSERT INTO boards (name) VALUES ('WOW');
INSERT INTO boards (name) VALUES ('biker');
INSERT INTO boards (name) VALUES ('HardwareSale');
INSERT INTO boards (name) VALUES ('ONE_PIECE');
INSERT INTO boards (name) VALUES ('Tennis');
INSERT INTO boards (name) VALUES ('Monkeys');
INSERT INTO boards (name) VALUES ('FATE_GO');
INSERT INTO boards (name) VALUES ('Isayama');
INSERT INTO boards (name) VALUES ('CVS');
INSERT INTO boards (name) VALUES ('mobilesales');
INSERT INTO boards (name) VALUES ('Lakers');
INSERT INTO boards (name) VALUES ('Hsinchu');
INSERT INTO boards (name) VALUES ('Salary');
INSERT INTO boards (name) VALUES ('MLB');
INSERT INTO boards (name) VALUES ('PublicServan');
INSERT INTO boards (name) VALUES ('KoreanPop');
INSERT INTO boards (name) VALUES ('AC_In');
INSERT INTO boards (name) VALUES ('TypeMoon');
INSERT INTO boards (name) VALUES ('EAseries');
INSERT INTO boards (name) VALUES ('MuscleBeach');
INSERT INTO boards (name) VALUES ('NBA_Film');
INSERT INTO boards (name) VALUES ('YuanChuang');
INSERT INTO boards (name) VALUES ('AzurLane');
INSERT INTO boards (name) VALUES ('Food');
INSERT INTO boards (name) VALUES ('MacShop');
INSERT INTO boards (name) VALUES ('Aviation');
INSERT INTO boards (name) VALUES ('China-Drama');
INSERT INTO boards (name) VALUES ('Examination');
INSERT INTO boards (name) VALUES ('Gamesale');
INSERT INTO boards (name) VALUES ('GetMarry');
INSERT INTO boards (name) VALUES ('forsale');
INSERT INTO boards (name) VALUES ('CarShop');
INSERT INTO boards (name) VALUES ('Headphone');
INSERT INTO boards (name) VALUES ('PCReDive');
INSERT INTO boards (name) VALUES ('Soft_Job');
INSERT INTO boards (name) VALUES ('TY_Research');
INSERT INTO boards (name) VALUES ('medstudent');
INSERT INTO boards (name) VALUES ('PathofExile');
INSERT INTO boards (name) VALUES ('IZONE');
INSERT INTO boards (name) VALUES ('E-appliance');
INSERT INTO boards (name) VALUES ('HelpBuy');
INSERT INTO boards (name) VALUES ('watch');
INSERT INTO boards (name) VALUES ('cat');
INSERT INTO boards (name) VALUES ('part-time');
INSERT INTO boards (name) VALUES ('gay');
INSERT INTO boards (name) VALUES ('Finance');
INSERT INTO boards (name) VALUES ('Military');
INSERT INTO boards (name) VALUES ('MobilePay');
INSERT INTO boards (name) VALUES ('Wanted');
INSERT INTO boards (name) VALUES ('BB-Love');
INSERT INTO boards (name) VALUES ('basketballTW');
INSERT INTO boards (name) VALUES ('Korea_Travel');
INSERT INTO boards (name) VALUES ('BabyProducts');
INSERT INTO boards (name) VALUES ('give');
INSERT INTO boards (name) VALUES ('DC_SALE');
INSERT INTO boards (name) VALUES ('lesbian');
INSERT INTO boards (name) VALUES ('tabletennis');
INSERT INTO boards (name) VALUES ('DSLR');
INSERT INTO boards (name) VALUES ('cookclub');
INSERT INTO boards (name) VALUES ('Zastrology');
INSERT INTO boards (name) VALUES ('Chelsea');
INSERT INTO boards (name) VALUES ('Brand');
INSERT INTO boards (name) VALUES ('Nogizaka46');
INSERT INTO boards (name) VALUES ('FITNESS');
INSERT INTO boards (name) VALUES ('AKB48');
INSERT INTO boards (name) VALUES ('hypermall');
INSERT INTO boards (name) VALUES ('KanColle');
INSERT INTO boards (name) VALUES ('Gov_owned');
INSERT INTO boards (name) VALUES ('nb-shopping');
INSERT INTO boards (name) VALUES ('SuperJunior');
INSERT INTO boards (name) VALUES ('DMM_GAMES');
INSERT INTO boards (name) VALUES ('feminine_sex');
INSERT INTO boards (name) VALUES ('StarCraft');
INSERT INTO boards (name) VALUES ('job');
INSERT INTO boards (name) VALUES ('Bank_Service');
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