create table conversation (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `user_one` bigint,
    `user_two` bigint,
    `conversation_created_date` DATE,
    Primary Key(`id`),
    Foreign Key(`user_one`) references user(id),
    foreign key(`user_two`) references user(id)
);

create table message (
 `id` bigint NOT NULL AUTO_INCREMENT,
 `message_content` VARCHAR(255),
 `send_date` DATE,
 `sender` bigint,
 `conversation` bigint,
 primary key(id),
 foreign key(`sender`) references user(id),
 foreign key(`conversation`) references conversation(id)
);