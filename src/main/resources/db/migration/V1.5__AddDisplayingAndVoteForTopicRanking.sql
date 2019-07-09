alter table post
add column post_topic BIT(1) default 0;

alter table topic
add column displayed int default 0;