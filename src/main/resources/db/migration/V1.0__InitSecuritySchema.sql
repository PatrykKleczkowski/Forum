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
PRIMARY KEY (`id`),
constraint FKrhfovtciq1l558cw6udg0h0d3 foreign key(id_role) references role (id) on delete cascade);
