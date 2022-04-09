/* Create Tables*/
CREATE TABLE `cart_items`
(
    `id`         BIGINT(20) NOT NULL AUTO_INCREMENT,
    `product_id` BIGINT(20) NULL DEFAULT NULL,
    `user_id`    BIGINT(20) NULL DEFAULT NULL,
    `quantity`   INT(11)    NULL DEFAULT NULL,
    `formalized` TINYINT(4) NULL DEFAULT '0',
    `date`       DATE       NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `fk_cart_product` (`product_id`) USING BTREE,
    INDEX `fk_cart_user` (`user_id`) USING BTREE,
    CONSTRAINT `fk_cart_product` FOREIGN KEY (`product_id`) REFERENCES `onlinestore`.`product` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT `fk_cart_user` FOREIGN KEY (`user_id`) REFERENCES `onlinestore`.`user` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE
)
    COLLATE = 'utf8mb4_0900_ai_ci'
    ENGINE = InnoDB
    AUTO_INCREMENT = 35;

CREATE TABLE `product`
(
    `id`       BIGINT(20)    NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(255)  NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
    `cpu`      VARCHAR(255)  NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
    `filename` VARCHAR(255)  NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
    `price`    DOUBLE(22, 0) NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
)
    COLLATE = 'utf8mb4_0900_ai_ci'
    ENGINE = InnoDB
    AUTO_INCREMENT = 38;

CREATE TABLE `user`
(
    `id`       BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `active`   BIT(1)       NOT NULL,
    `username` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
    `email`    VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
    `password` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
    PRIMARY KEY (`id`) USING BTREE
)
    COLLATE = 'utf8mb4_0900_ai_ci'
    ENGINE = InnoDB
    AUTO_INCREMENT = 24;

CREATE TABLE `user_role`
(
    `user_id` BIGINT(20)   NOT NULL,
    `roles`   VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
    INDEX `FK859n2jvi8ivhui0rl0esws6o` (`user_id`) USING BTREE,
    CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `onlinestore`.`user` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE
)
    COLLATE = 'utf8mb4_0900_ai_ci'
    ENGINE = InnoDB;
