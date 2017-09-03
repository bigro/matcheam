CREATE SCHEMA IF NOT EXISTS MATCHEAM;

/*
 * match 募集
 */
CREATE TABLE MATCHEAM."match" (
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
CREATE TABLE MATCHEAM."entry" (
identifier VARCHAR(100) PRIMARY KEY AUTO_INCREMENT,
matchId VARCHAR(100) not null,
UNIQUE (matchId)
);

/*
 * entryUser 応募者
 */
CREATE TABLE MATCHEAM."entry_user" (
identifier VARCHAR(100) PRIMARY KEY AUTO_INCREMENT,
entryId VARCHAR(100) not null,
entryUserName VARCHAR(20),
FOREIGN KEY (entryId) REFERENCES MATCHEAM."entry" (identifier)
);
