CREATE SCHEMA IF NOT EXISTS matcheam;

/*
 * match 募集
 */
CREATE TABLE matcheam.match (
identifier VARCHAR(100) PRIMARY KEY AUTO_INCREMENT,
place VARCHAR(100),
date DATE,
start VARCHAR(10),
time VARCHAR(10),
level VARCHAR(30),
maxPlayers DECIMAL(3)
);

/*
 * entry 応募
 */
CREATE TABLE matcheam.entry (
identifier VARCHAR(100) PRIMARY KEY AUTO_INCREMENT,
matchId VARCHAR(100) not null,
entryUserId VARCHAR(100) not null
);

/*
 * entryUser 応募者
 */
CREATE TABLE matcheam.entryUser (
identifier VARCHAR(100) PRIMARY KEY AUTO_INCREMENT,
entryUserName VARCHAR(20)
);
