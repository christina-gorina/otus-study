insert into author (id, `name`) values (1, 'Orlov G. O.');
insert into author (id, `name`) values (2, 'Somov E. V.');
insert into author (id, `name`) values (3, 'Levin O. F.');
insert into author (id, `name`) values (4, 'Gromov N. V.');
insert into author (id, `name`) values (5, 'Umov D. M.');
insert into author (id, `name`) values (6, 'Radov K. N.');
insert into genre (id, `name`) values (1, 'Mystic');
insert into genre (id, `name`) values (2, 'Fantastic');
insert into genre (id, `name`) values (3, 'Horror');
insert into book (id, `name`, `genre_id`, `owner`) values (1, 'Love story', 1, 'admin');
insert into book (id, `name`, `genre_id`, `owner`) values (2, 'Call of the night', 2, 'user');
insert into book (id, `name`, `genre_id`, `owner`) values (3, 'Close your eyes', 3, 'user');
insert into book_authors (`book_id`, `author_id`) values (1, 1);
insert into book_authors (`book_id`, `author_id`) values (2, 2);
insert into book_authors (`book_id`, `author_id`) values (3, 3);
insert into book_authors (`book_id`, `author_id`) values (1, 4);
insert into book_authors (`book_id`, `author_id`) values (2, 5);
insert into book_authors (`book_id`, `author_id`) values (3, 6);
insert into comment (id, `book_id`, `text`) values (1, 1, 'Comment 1 for book 1');
insert into comment (id, `book_id`, `text`) values (2, 1, 'Comment 2 for book 1');
insert into comment (id, `book_id`, `text`) values (3, 1, 'Comment 3 for book 1');
insert into comment (id, `book_id`, `text`) values (4, 1, 'Comment 4 for book 1');
insert into comment (id, `book_id`, `text`) values (5, 1, 'Comment 5 for book 1');
insert into comment (id, `book_id`, `text`) values (6, 2, 'Comment 6 for book 2');
insert into comment (id, `book_id`, `text`) values (7, 2, 'Comment 7 for book 2');
insert into comment (id, `book_id`, `text`) values (8, 2, 'Comment 8 for book 2');
insert into comment (id, `book_id`, `text`) values (9, 2, 'Comment 9 for book 2');
insert into comment (id, `book_id`, `text`) values (10, 2, 'Comment 10 for book 2');
insert into comment (id, `book_id`, `text`) values (11, 3, 'Comment 11 for book 3');
insert into comment (id, `book_id`, `text`) values (12, 3, 'Comment 12 for book 3');
insert into comment (id, `book_id`, `text`) values (13, 3, 'Comment 13 for book 3');
insert into comment (id, `book_id`, `text`) values (14, 3, 'Comment 14 for book 3');
insert into comment (id, `book_id`, `text`) values (15, 3, 'Comment 15 for book 3');
insert into users (id, `name`, `password`, `email`) values (1, 'admin', 'password', 'admin@gmail.com');
insert into users (id, `name`, `password`, `email`) values (2, 'user', 'password', 'user@gmail.com');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 2),
       ('ADMIN', 1);
