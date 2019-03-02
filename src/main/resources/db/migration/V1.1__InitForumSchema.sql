CREATE TABLE `category` (
  `id` bigINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(40),
  PRIMARY KEY(`id`)
);

CREATE TABLE `topic` (
  `id` bigINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(40) NOT NULL,
  `author_id` bigINT NOT NULL,
  `category_id` bigINT NOT NULL,
  `created_date` DATETIME NOT NULL,
  `pinned` BIT(1) DEFAULT 1,
  `enabledForUsers` BIT(1) DEFAULT 1,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`category_id`) references `category`(`id`) on delete no action on update cascade,
  FOREIGN KEY (`author_id`) references `user`(`id`) on delete no action on update cascade
  );


CREATE TABLE `post` (
  `id` bigINT NOT NULL AUTO_INCREMENT,
  `author_id` bigINT NOT NULL,
  `content` LONGTEXT NOT NULL,
  `created_date` DATETIME NOT NULL,
  `topic_id` bigINT NOT NULL,
  `like` INT DEFAULT 0,
  `edited` Bit(1) DEFAULT 0,
  PRIMARY KEY (`id`),
  FOREIGN KEY(`topic_id`) references topic(`id`) on delete no action on update cascade,
  FOREIGN KEY(`author_id`) references user(`id`) on delete no action on update cascade
  );

CREATE TABLE `comment` (
  `id` bigINT NOT NULL AUTO_INCREMENT,
  `author_id` bigINT NOT NULL,
  `content` LONGTEXT NOT NULL,
  `created_date` DATE NOT NULL,
  `post_id` bigINT NOT NULL,
  PRIMARY KEY (`id`),
   FOREIGN KEY(`author_id`) references user(`id`) on delete no action on update cascade,
   FOREIGN KEY(`post_id`) references post(`id`) on delete no action on update cascade
   );