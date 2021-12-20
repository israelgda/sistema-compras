INSERT INTO tb_usuarios (nome, email, password) VALUES ('Daniel', 'daniel@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_usuarios (nome, email, password) VALUES ('Israel', 'israel@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_roles (authority) VALUES ('ROLE_USUARIO');
INSERT INTO tb_roles (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_usuarios_roles (usuario_id, role_id) VALUES (1, 1);
INSERT INTO tb_usuarios_roles (usuario_id, role_id) VALUES (2, 2);