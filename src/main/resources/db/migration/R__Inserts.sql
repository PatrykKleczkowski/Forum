INSERT INTO role (id, description, name) VALUES (1, 'Admin role', 'ROLE_ADMIN');
INSERT INTO role (id, description, name) VALUES (2, 'User role', 'ROLE_USER');

INSERT INTO user(id, username, password, id_role,rank_name,points) VALUES
 (1, 'Admin', '$2a$04$iRsccpxqihb7QvTewwyncOVpMTF/xLX4YekCDIgUi4b.BBzM4uRdi',1,'NOWY', 5);

INSERT INTO user(id, username, password,id_role) VALUES
(2, 'User', '$2a$04$gpcSxxNLO/pNfvxVDCAFqeInF9RrmQadtyMFX9Mywum22eEz7Lvqa',2);

INSERT INTO category(id, title, category_type,description) VALUES (1, 'Java', 'Programowanie',  'Java jest językiem tworzenia programów źródłowych kompilowanych do kodu bajtowego, czyli postaci wykonywanej przez maszynę wirtualną. Język cechuje się silnym typowaniem..');
INSERT INTO category(id, title, category_type,description) VALUES (2, 'C#', 'Programowanie','Opis');
INSERT INTO category(id, title, category_type,description) VALUES (3, 'PHP', 'Język skryptowy','Opis');
INSERT INTO category(id, title, category_type,description) VALUES (4, 'Go', 'Programowanie','Opis');
INSERT INTO category(id, title, category_type,description) VALUES (5, 'C', 'Programowanie','Opis');
INSERT INTO category(id, title, category_type,description) VALUES (6, 'JavaScript', 'Język skryptowy','Opis');
INSERT INTO category(id, title, category_type,description) VALUES (7, 'Python', 'Język skryptowy','Opis');
INSERT INTO category(id, title, category_type,description) VALUES (8, 'MySQL', 'Bazy danych','Opis');
INSERT INTO category(id, title, category_type,description) VALUES (9, 'PostgreSQL', 'Bazy danych','Opis');
INSERT INTO category(id, title, category_type,description) VALUES (10, 'NoSql', 'Bazy danych','Opis');
INSERT INTO category(id, title, category_type,description) VALUES (11, 'Oracle', 'Bazy danych','Opis');
INSERT INTO category(id, title, category_type,description) VALUES (12, 'OffTop - rozmowy o wszystkim', 'forum','Opis');
INSERT INTO category(id, title, category_type,description) VALUES (13, 'Sprzedam/Kupię', 'forum','Opis');
INSERT INTO category(id, title, category_type,description) VALUES (14, 'Szukam/Dam pracę', 'forum','Opis');
INSERT INTO category(id, title, category_type,description) VALUES (99, 'Śmietnik', 'forum','Opis');

#
# INSERT INTO topic(id, title, author_id, category_id, created_date) VALUES (1, 'Witamy na forum!', 1, 1, '2019-01-16');
# INSERT INTO topic(id, title, author_id, category_id, created_date) VALUES (2, 'Programowanie Java - nowości', 1, 1, '2019-01-17');
# INSERT INTO topic(id, title, author_id, category_id, created_date) VALUES (3, 'Hosting dla aplikacji webowych na platformę Java', 1, 1, '2019-01-16');
# INSERT INTO topic(id, title, author_id, category_id, created_date) VALUES (4, 'Materiały dostępne w sieci', 1, 1, '2019-01-16');
# INSERT INTO topic(id, title, author_id, category_id, created_date) VALUES (5, 'Jaki wzorzec projektowy do tego?', 1, 1, '2019-01-16');
#
# INSERT INTO vote(id, likes) VALUES (1, 5);
# INSERT INTO vote(id, likes) VALUES (2, 1);
#
#
# INSERT INTO post(id, author_id, post_content, created_date, topic_id, edited, vote_id)
# VALUES (1, 1, 'Jest 10 zmiennych do przetworzenia w różnych wariantach, a każdy wariant ma różne opcje, które potem też mają różne opcje/parametry. Potrzebny jest każdy z wariantów i próbuję to upchnać. Napiszę pseudokodem co ma to robić w tych 2 wariantach.', '2019-01-16', 1, 1, 1);
#
# INSERT INTO post(id, author_id, post_content, created_date, topic_id, edited, vote_id)
# VALUES (2, 1, 'Dobre hehehehehe', '2019-01-17', 1, 1, 2);
#
#
# INSERT INTO comment(id, author_id, comment_content, created_date, post_id)
# VALUES (1, 1, 'Nie zgadzam sie!', '2019-01-16', 1);
# INSERT INTO comment(id, author_id, comment_content, created_date, post_id)
# VALUES (2, 2, 'A ja popieram, świetny post!', '2019-01-17', 1);

