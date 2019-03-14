INSERT INTO role (id, description, name) VALUES (1, 'Admin role', 'ROLE_ADMIN');
INSERT INTO role (id, description, name) VALUES (2, 'User role', 'ROLE_USER');

INSERT INTO user(id, username, password, id_role) VALUES
 (1, 'Admin', '$2a$04$iRsccpxqihb7QvTewwyncOVpMTF/xLX4YekCDIgUi4b.BBzM4uRdi',1);

INSERT INTO user(id, username, password,id_role) VALUES
(2, 'User', '$2a$04$gpcSxxNLO/pNfvxVDCAFqeInF9RrmQadtyMFX9Mywum22eEz7Lvqa',2);

INSERT INTO category(id, title) VALUES (1, 'Web development');
INSERT INTO category(id, title) VALUES (2, 'Artificial intelligence');
INSERT INTO category(id, title) VALUES (3, 'Machine learning');
INSERT INTO category(id, title) VALUES (4, 'New technologies');
INSERT INTO category(id, title) VALUES (5, 'Off-top');



INSERT INTO topic(id, title, author_id, category_id, created_date) VALUES (1, 'Witamy na forum!', 1, 1, '2019-01-16');
INSERT INTO topic(id, title, author_id, category_id, created_date) VALUES (2, 'Programowanie Java - nowości', 1, 1, '2019-01-17');
INSERT INTO topic(id, title, author_id, category_id, created_date) VALUES (3, 'Hosting dla aplikacji webowych na platformę Java', 1, 1, '2019-01-16');
INSERT INTO topic(id, title, author_id, category_id, created_date) VALUES (4, 'Materiały dostępne w sieci', 1, 1, '2019-01-16');
INSERT INTO topic(id, title, author_id, category_id, created_date) VALUES (5, 'Jaki wzorzec projektowy do tego?', 1, 1, '2019-01-16');




