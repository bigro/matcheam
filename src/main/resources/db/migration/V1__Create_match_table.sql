create table matcheam.match (
    identifier varchar(256) not null,
    place varchar(100) not null,
    date DATETIME　not null,
    gameTime TIME not null,
    level varchar(100) not null,
    maxPlayers int
);
