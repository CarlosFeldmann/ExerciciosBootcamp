CREATE DATABASE `ml_app_consultorio`
    COLLATE 'utf8_spanish_ci';

CREATE USER 'ml_app_user1'@'localhost'
    IDENTIFIED BY 'ml_app_user1';

CREATE USER 'ml_app_user2'@'localhost'
    IDENTIFIED BY 'ml_app_user2';

CREATE USER 'ml_app_user3'@'localhost'
    IDENTIFIED BY 'ml_app_user3';


GRANT INSERT, SELECT, UPDATE , DELETE,
    INDEX, ALTER, CREATE, CREATE TEMPORARY TABLES, CREATE VIEW, REFERENCES
    on ml_app_consultorio.* TO 'ml_app_user1'@'localhost';

GRANT ALL PRIVILEGES
    on ml_app_consultorio.* TO 'ml_app_user2'@'localhost';

GRANT SELECT
    on ml_app_consultorio.* TO 'ml_app_user3'@'localhost';


