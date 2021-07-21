CREATE DATABASE `ml_app_consultorio`
    COLLATE 'utf8_spanish_ci';

CREATE USER 'ml_app_user1'@'localhost'
    IDENTIFIED BY 'ml_app_user1';

CREATE USER 'ml_app_user2'@'localhost'
    IDENTIFIED BY 'ml_app_user2';


CREATE USER 'ml_app_user3'@'localhost'
    IDENTIFIED BY 'ml_app_user3';


--- Since i'm using docker to create a mysql database, and running spring without it, i am not in the localhost of the db
--- So, i created this new user to allow access from any ip, because i don't want to hardcode my localip here
--- I understand that this is a issue on a production server, but in development that's fine
CREATE USER 'ml_app_user4'@'%'
    IDENTIFIED BY 'ml_app_user4';


GRANT INSERT, SELECT, UPDATE , DELETE,
    INDEX, ALTER, CREATE, CREATE TEMPORARY TABLES, CREATE VIEW, REFERENCES
    on ml_app_consultorio.* TO 'ml_app_user1'@'localhost';

GRANT ALL PRIVILEGES
    on ml_app_consultorio.* TO 'ml_app_user2'@'localhost';

GRANT ALL PRIVILEGES
    on ml_app_consultorio.* TO 'ml_app_user4'@'%';


GRANT SELECT
    on ml_app_consultorio.* TO 'ml_app_user3'@'localhost';


