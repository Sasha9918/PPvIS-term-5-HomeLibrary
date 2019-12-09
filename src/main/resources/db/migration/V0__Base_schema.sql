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

------------------------------------------------------------------------------------------------------------------------
--- TABLES
------------------------------------------------------------------------------------------------------------------------

CREATE TABLE book
(
    id              UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    author          VARCHAR(255)        NOT NULL,
    book_name       VARCHAR(255) PRIMARY KEY UNIQUE NOT NULL,
    publisher       VARCHAR(255),
    publishing_year int
);

CREATE TABLE book_catalog
(
    id        UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    book_name VARCHAR(255) UNIQUE NOT NULL,
    rate      DOUBLE PRECISION    NOT NULL,
    status    VARCHAR(32)         NOT NULL
);

CREATE TABLE visitor_profile
(
    id              UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    first_name      VARCHAR(255)        NOT NULL,
    last_name       VARCHAR(255) UNIQUE NOT NULL,
    role            VARCHAR(30)         NOT NULL,
    favourite       text[],
    planned_to_read text[],
    read            text[]
);

CREATE TABLE book_storage
(
    id        UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    book_name VARCHAR(255) UNIQUE NOT NULL,
    genre     VARCHAR(32)         NOT NULL,
    subject   VARCHAR(32)         NOT NULL
);

CREATE TABLE visitor_counting
(
    id                UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    book_name         VARCHAR(255) UNIQUE NOT NULL,
    visitor_last_name VARCHAR(255)        NOT NULL,
    taken_date        timestamp           NOT NULL,
    returned_date     timestamp
);
