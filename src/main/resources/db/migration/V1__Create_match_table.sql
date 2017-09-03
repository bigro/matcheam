/*
 * match 募集
 */
CREATE TABLE matcheam.match (
identifier INTEGER PRIMARY KEY AUTO_INCREMENT,
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
identifier INTEGER PRIMARY KEY AUTO_INCREMENT,
matchId INTEGER not null,
UNIQUE (matchId)
);

/*
 * entryUser 応募者
 */
CREATE TABLE matcheam.entry_user (
identifier INTEGER PRIMARY KEY AUTO_INCREMENT,
entryId INTEGER not null,
entryUserName VARCHAR(20),
FOREIGN KEY (entryId) REFERENCES matcheam.entry (identifier)
);
