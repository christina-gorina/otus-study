DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS user;

CREATE TABLE author
(
    id   IDENTITY NOT NULL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE genre
(
    id   IDENTITY NOT NULL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE book
(
    id        IDENTITY NOT NULL PRIMARY KEY,
    name      VARCHAR(255),
    genre_id  BIGINT   NOT NULL,
    FOREIGN KEY (genre_id) REFERENCES genre (id) ON DELETE CASCADE
);

CREATE TABLE book_authors
(
    book_id   BIGINT REFERENCES book (id) ON DELETE CASCADE,
    author_id BIGINT REFERENCES author (id),
    PRIMARY KEY (book_id, author_id)
);

CREATE TABLE users
(
    id               IDENTITY NOT NULL PRIMARY KEY,
    name             VARCHAR                           NOT NULL,
    password         VARCHAR                           NOT NULL,
    email            VARCHAR
);
