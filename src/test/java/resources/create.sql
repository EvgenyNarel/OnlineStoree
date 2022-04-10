CREATE TABLE `user`
(
    `id`     BIGINT NOT NULL AUTO_INCREMENT,
    `active` Bit(1) NOT NULL,
    `username` VARCHAR(150) NOT NULL,
    `email` VARCHAR(255) NULL DEFAULT NULL ,
    `password` VARCHAR(255) NULL DEFAULT NULL
)
;
