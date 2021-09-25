DROP TABLE IF EXISTS user_roles;
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
    owner     VARCHAR(255)   NOT NULL,
    FOREIGN KEY (genre_id) REFERENCES genre (id) ON DELETE CASCADE
);

CREATE TABLE book_authors
(
    book_id   BIGINT REFERENCES book (id) ON DELETE CASCADE,
    author_id BIGINT REFERENCES author (id),
    PRIMARY KEY (book_id, author_id)
);

CREATE TABLE comment
(
    id      IDENTITY NOT NULL PRIMARY KEY,
    text    VARCHAR(255),
    book_id BIGINT   NOT NULL,
    FOREIGN KEY (book_id) REFERENCES book (id) ON DELETE CASCADE
);

CREATE TABLE users
(
    id               IDENTITY NOT NULL PRIMARY KEY,
    name             VARCHAR                           NOT NULL,
    password         VARCHAR                           NOT NULL,
    email            VARCHAR
);

CREATE TABLE roles
(
    id INTEGER NOT NULL,
    name    VARCHAR
);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
