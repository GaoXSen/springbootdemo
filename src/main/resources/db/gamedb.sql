
create database game;

CREATE TABLE game_record (
                             id BIGINT PRIMARY KEY AUTO_INCREMENT,
                             player_name VARCHAR(50),
                             score INT,
                             create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);