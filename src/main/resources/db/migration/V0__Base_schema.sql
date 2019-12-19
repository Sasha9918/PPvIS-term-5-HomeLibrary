------------------------------------------------------------------------------------------------------------------------
--- EXTENSIONS
------------------------------------------------------------------------------------------------------------------------
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

------------------------------------------------------------------------------------------------------------------------
--- TYPES
------------------------------------------------------------------------------------------------------------------------
CREATE TYPE role AS ENUM ('ROLE_OWNER', 'ROLE_VISITOR' );
CREATE TYPE status AS ENUM ('PRIVATE', 'PUBLIC' );
CREATE TYPE subject AS ENUM ('CHILDISH', 'EDUCATIONAL', 'FICTION', 'SCIENCE' );
CREATE TYPE genre AS ENUM ('DETECTIVE', 'FAIRYTALE', 'FANTASY', 'LEGEND', 'NOVEL', 'POETRY', 'ASTRONOMY', 'BIOLOGY',
    'MATH', 'CHEMISTRY', 'MEDICINE', 'PSYCHOLOGY', 'PHYSICS' );

------------------------------------------------------------------------------------------------------------------------
--- TABLES
------------------------------------------------------------------------------------------------------------------------

CREATE TABLE book
(
    author          VARCHAR(255) NOT NULL,
    book_name       VARCHAR(255) PRIMARY KEY,
    publisher       VARCHAR(255),
    publishing_year int
);

CREATE TABLE book_catalog
(
    book_name VARCHAR(255) PRIMARY KEY,
    rate      DOUBLE PRECISION NOT NULL,
    status    VARCHAR(32)      NOT NULL
);

CREATE TABLE visitor_profile
(
    first_name      VARCHAR(255) NOT NULL,
    last_name       VARCHAR(255) PRIMARY KEY,
    role            VARCHAR(30)  NOT NULL,
    favourite       text[],
    planned_to_read text[],
    read            text[]
);

CREATE TABLE book_storage
(
    book_name VARCHAR(255) PRIMARY KEY,
    genre     VARCHAR(32) NOT NULL,
    subject   VARCHAR(32) NOT NULL
);

CREATE TABLE visitor_counting
(
    id                UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    book_name         VARCHAR(255) NOT NULL,
    visitor_last_name VARCHAR(255) NOT NULL,
    taken_date        timestamp    NOT NULL,
    returned_date     timestamp
);
