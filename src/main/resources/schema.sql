CREATE SCHEMA IF NOT EXISTS matcheam;

CREATE TABLE matcheam.match (
identifier VARCHAR(100) PRIMARY KEY AUTO_INCREMENT,
date DATETIME,
gameTime TIME,
level VARCHAR(30),
maxPlayers INT
);