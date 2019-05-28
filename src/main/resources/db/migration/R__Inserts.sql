INSERT INTO role (id, description, name) VALUES (1, 'Admin role', 'ROLE_ADMIN');
INSERT INTO role (id, description, name) VALUES (2, 'User role', 'ROLE_USER');

INSERT INTO user(id, username, password, id_role,rank_name,points) VALUES
 (1, 'Admin', '$2a$04$iRsccpxqihb7QvTewwyncOVpMTF/xLX4YekCDIgUi4b.BBzM4uRdi',1,'NOWY', 5);

INSERT INTO user(id, username, password,id_role) VALUES
(2, 'User', '$2a$04$gpcSxxNLO/pNfvxVDCAFqeInF9RrmQadtyMFX9Mywum22eEz7Lvqa',2);

INSERT INTO category(id, title, category_type,description) VALUES (1, 'Java', 'Programowanie',  'Forum poświęcone językowi Java oraz wszystkim innym technologiom związanym z JVM i platformą Java.');
INSERT INTO category(id, title, category_type,description) VALUES (2, 'C#', 'Programowanie','Forum poświęcone językowi C# oraz ogólnie platformie .NET.');
INSERT INTO category(id, title, category_type,description) VALUES (3, 'PHP', 'Język skryptowy','Dyskusje poświęcone programowaniu w języku PHP.');
INSERT INTO category(id, title, category_type,description) VALUES (4, 'Go', 'Programowanie',' język programowania opracowany przez pracowników Google: Roberta Griesemera, Roba Pike''a oraz Kena Thompsona.');
INSERT INTO category(id, title, category_type,description) VALUES (5, 'C', 'Programowanie','Tutaj możesz dyskutować o czystym C, C++, a także o problemach w środowiskach np. C++ Builder, Visual C++.');
INSERT INTO category(id, title, category_type,description) VALUES (6, 'JavaScript', 'Język skryptowy','Forum poświęcone językowi, którego popularność stale rośnie, czyli JavaScript. Tutaj możesz pisać na temat Node.JS, ES6, frameworkach takich jak Angular.JS, Vue.JS itp.');
INSERT INTO category(id, title, category_type,description) VALUES (7, 'Python', 'Język skryptowy','Wszystkie tematy związane z językiem Python, w tym Django, Flask, Pyramid, Twisted czy Tornado.');
INSERT INTO category(id, title, category_type,description) VALUES (8, 'MySQL', 'Bazy danych','Forum poświęcone zagadnieniom związanym z serwerami baz danych MySQL');
INSERT INTO category(id, title, category_type,description) VALUES (9, 'PostgreSQL', 'Bazy danych','Forum poświęcone zagadnieniom związanym z serwerami baz danych PostgreSQL');
INSERT INTO category(id, title, category_type,description) VALUES (10, 'NoSql', 'Bazy danych','Forum poświęcone zagadnieniom związanym z serwerami baz danych NoSql');
INSERT INTO category(id, title, category_type,description) VALUES (11, 'Oracle', 'Bazy danych','Forum poświęcone zagadnieniom związanym z serwerami baz danych Oracle');
INSERT INTO category(id, title, category_type,description) VALUES (12, 'OffTop - rozmowy o wszystkim', 'forum','Miejsce na dyskusje niepasujące do pozostałych kategorii forum, niekoniecznie związane z programowaniem.');
INSERT INTO category(id, title, category_type,description) VALUES (13, 'Sprzedam/Kupię', 'forum','Tutaj możesz zamieszczać ogłoszenia kupna, sprzedaży towarów i usług oraz inne drobne ogłoszenia (w tym wolontariatu).');
INSERT INTO category(id, title, category_type,description) VALUES (14, 'Szukam/Dam pracę', 'forum','W tym dziale możesz podyskutować o poszukiwaniu pracy, rozmowach kwalifikacyjnych, zarobkach i wszystkim co jest związane z przelewaniem swych umiejętności na pieniądze.');
INSERT INTO category(id, title, category_type,description) VALUES (99, 'Śmietnik', 'forum','Zawiera tematy usunięte z forum. Tematy z tego działu są automatycznie czyszczone po 7 dniach.');



