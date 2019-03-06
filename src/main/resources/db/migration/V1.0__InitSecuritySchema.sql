CREATE TABLE `role` (
`id` bigint NOT NULL AUTO_INCREMENT,
`description` varchar(255),
`name` varchar(255),
PRIMARY KEY (`id`));

CREATE TABLE `user` (
`id` bigint NOT NULL AUTO_INCREMENT,
`username` varchar(255),
`password` varchar(255),
`enabled` BIT(1) NOT NULL DEFAULT 1,
`active` BIT(1) NOT NULL DEFAULT 1,
`id_role` bigint,
`last_login` DATETIME default null,
`registered` DATETIME default null,
PRIMARY KEY (`id`),
foreign key(id_role) references role (id) on delete cascade);
