insert into author (id, `name`) values (1, 'Orlov G. O.');
insert into author (id, `name`) values (2, 'Somov E. V.');
insert into author (id, `name`) values (3, 'Levin O. F.');
insert into genre (id, `name`) values (1, 'Mystic');
insert into genre (id, `name`) values (2, 'Fantastic');
insert into genre (id, `name`) values (3, 'Horror');
insert into book (id, `name`, `author_id`, `genre_id`) values (1, 'Love story', 1, 1);
insert into book (id, `name`, `author_id`, `genre_id`) values (2, 'Call of the night', 2, 2);
insert into book (id, `name`, `author_id`, `genre_id`) values (3, 'Close your eyes', 3, 3);
