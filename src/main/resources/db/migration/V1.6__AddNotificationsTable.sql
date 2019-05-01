CREATE TABLE `notification` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `received_user_id` BIGINT(11) NOT NULL,
  `title` VARCHAR(256) NOT NULL,
  `message` VARCHAR(768) NOT NULL,
  `date_and_time` DATETIME NOT NULL,
  `post_id` BIGINT(11) NOT NULL,
  `displayed` BIT(1) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`received_user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE);
  );

