INSERT INTO country (name, continent) VALUES
('Macedonia', 'Europe'),
('France', 'Europe'),
('USA', 'North America');

insert into author (name, surname, country_id) values
('Blaze', 'Koneski', (select id from country where name = 'Macedonia')),
('Edgar Alan', 'Poe', (select id from country where name = 'USA')),
('Victor', 'Hugo', (select id from country where name = 'France'));

insert into book (name, category, available_copies) values
('Les Miserables', 'NOVEL', 3),
('Raven', 'CLASSICS', 10),
('За македонските работи', 'HISTORY', 10);

insert into book_authors(book_id, authors_id) values
((select id from book where name = 'Les Miserables'), (select id from author where name = 'Victor' and surname = 'Hugo')),
((select id from book where name = 'Raven'), (select id from author where name = 'Edgar Alan' and surname = 'Poe')),
((select id from book where name = 'За македонските работи'), (select id from author where name = 'Blaze' and surname = 'Koneski'));
