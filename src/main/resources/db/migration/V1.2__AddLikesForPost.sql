create table vote (
id BIGINT NOT NULL AUTO_INCREMENT,
likes INT default 0,
dislikes INT default 0,
Primary Key(id)
);

alter table post
add column vote_id BIGINT,
add column likes int,
add foreign key(vote_id) references vote(id);

create table `user_vote` (
`user_id` BIGINT,
`vote_id` BIGINT,
`liked` bit(1) default 0,
`disliked` bit(1) default 0,
FOREIGN KEY(`user_id`) references user(`id`),
foreign key(`vote_id`) references `vote`(`id`)
);