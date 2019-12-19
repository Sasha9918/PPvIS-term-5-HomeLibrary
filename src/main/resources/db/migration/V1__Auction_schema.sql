ALTER TABLE visitor_profile
    ADD login VARCHAR(255) UNIQUE;
ALTER TABLE visitor_profile
    ADD password VARCHAR(255) UNIQUE;

CREATE TABLE auction
(
    -- TODO: think about auction table architecture
);
