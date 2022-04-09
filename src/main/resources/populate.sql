INSERT INTO `product` (`id`, `name`, `cpu`, `filename`, `price`)
VALUES (1, 'Asus 200XTC', 'Intel pentium 2', 'i759b4eb2f.jpg', 2000),
       (5, 'HP', 'Intel i7', 'ib2ccea8e6.jpg', 2100),
       (33, 'HP', 'Intel i7', 'samsung.jpeg', 3000),
       (35, 'Lenovo', 'Intel pentium 2', 'lenovo.png', 1500);

INSERT INTO `user` (`id`, `active`, `username`, `email`, `password`)
VALUES (18, 1, 'Tom', 'narel28@mail.ru', '$2a$08$xaVIiphfc6z8z1Sx.J3QVuAxNvCyeN.lq74nYPWqvOCbOdWSLG/Gy');

INSERT INTO `user_role`(`user_id`, `roles`)
VALUES (18, 'ADMIN')
