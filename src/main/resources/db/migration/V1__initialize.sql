CREATE TABLE `user` (
    `id`          BIGINT NOT NULL AUTO_INCREMENT UNIQUE,
    `username` VARCHAR(64) NOT NULL,
    `email` VARCHAR(256) NOT NULL,
    `created`  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=INNODB;

CREATE TABLE `follower` (
    `user_id` BIGINT NOT NULL,
    `followee_id` BIGINT NOT NULL,
    `created`  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`user_id`, `followee_id`),
    FOREIGN KEY (`user_id`) REFERENCES user(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`followee_id`) REFERENCES user(`id`) ON DELETE CASCADE
) ENGINE=INNODB;

CREATE TABLE `tweet` (
    `id`          BIGINT NOT NULL AUTO_INCREMENT UNIQUE,
    `text` VARCHAR(160) NOT NULL,
    `user_id` BIGINT NOT NULL,
    `created`  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    INDEX `user_id_idx` (`user_id`),
    FULLTEXT `text_idx` (`text`)
) ENGINE=INNODB;
