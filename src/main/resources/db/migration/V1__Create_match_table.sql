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
max_players DECIMAL(3)
);

/*
 * entryUser 応募者
 */
CREATE TABLE matcheam.entry_user (
identifier INTEGER PRIMARY KEY AUTO_INCREMENT,
match_id INTEGER not null,
entry_user_name VARCHAR(20),
FOREIGN KEY (match_id) REFERENCES matcheam.match (identifier)
);
