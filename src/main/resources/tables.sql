CREATE TABLE bands
(
    id   SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE artists
(
    id   SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    band INTEGER REFERENCES bands (id)
);

CREATE TABLE stages
(
    id   SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE performances
(
    id    SERIAL PRIMARY KEY,
    name  TEXT      NOT NULL,
    band  INTEGER REFERENCES bands (id),
    stage INTEGER REFERENCES stages (id),
    time  TIMESTAMP NOT NULL
);