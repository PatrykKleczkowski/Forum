INSERT INTO role (id, description, name) VALUES (1, 'Admin role', 'ROLE_ADMIN');
INSERT INTO role (id, description, name) VALUES (2, 'User role', 'ROLE_USER');

INSERT INTO user(id, username, password, id_role) VALUES
 (1, 'Admin', '$2a$04$iRsccpxqihb7QvTewwyncOVpMTF/xLX4YekCDIgUi4b.BBzM4uRdi',1);

INSERT INTO user(id, username, password,id_role) VALUES
(2, 'User', '$2a$04$gpcSxxNLO/pNfvxVDCAFqeInF9RrmQadtyMFX9Mywum22eEz7Lvqa',2);

INSERT INTO category(id, title) VALUES (1, 'Programowanie');

INSERT INTO topic(id, title, author_id, category_id, created_date) VALUES (1, 'Witamy na forum!', 1, 1, '2019-01-16')

