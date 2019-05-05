create table conversation (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `user_one_id` bigint,
    `user_two_id` bigint,
    `conversation_created_date` DATE,
    Primary Key(`id`),
    Foreign Key(`user_one_id`) references user(id),
    foreign key(`user_two_id`) references user(id)
);

create table message (
 `id` bigint NOT NULL AUTO_INCREMENT,
 `message_content` VARCHAR(255),
 `send_date` DATE,
 `sender_id` bigint,
 `conversation_id` bigint,
 primary key(id),
 foreign key(`sender_id`) references user(id),
 foreign key(`conversation_id`) references conversation(id)
);