ALTER TABLE visitor_profile
    ADD login VARCHAR(255) UNIQUE;
ALTER TABLE visitor_profile
    ADD password VARCHAR(255) UNIQUE;

CREATE TABLE auction
(
    name        VARCHAR(255) PRIMARY KEY,
    books       text[],
    started_at  TIMESTAMP NOT NULL,
    finished_at TIMESTAMP NOT NULL
);
