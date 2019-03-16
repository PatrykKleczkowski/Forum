create table vote (
id BIGINT NOT NULL AUTO_INCREMENT,
likes INT default 0,
dislikes INT default 0,
Primary Key(id)
);

alter table post
add column vote_id BIGINT,
add foreign key(vote_id) references vote(id);

create table `user_votes` (
`vote_id` BIGINT,
`user_id` BIGINT,
FOREIGN KEY(`user_id`) references user(`id`),
foreign key(`vote_id`) references `vote`(`id`),
primary key(`vote_id`, `user_id`)
);