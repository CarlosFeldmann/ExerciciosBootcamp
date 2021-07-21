USE `ml_app_consultorio`;


CREATE TABLE IF NOT EXISTS test_table
(
    id        bigint auto_increment primary key,
    name      varchar(255) not null,
    birthdate date         not null
);

INSERT into test_table (name, birthdate)
VALUES ('Carlos', '1999-09-12'),
       ('Aaaaaaaa', '2012-05-11');
