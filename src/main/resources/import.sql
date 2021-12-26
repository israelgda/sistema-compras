INSERT INTO tb_usuarios (nome, email, password) VALUES ('Daniel', 'daniel@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_usuarios (nome, email, password) VALUES ('Israel', 'israel@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_roles (authority) VALUES ('ROLE_USUARIO');
INSERT INTO tb_roles (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_usuarios_roles (usuario_id, role_id) VALUES (1, 1);
INSERT INTO tb_usuarios_roles (usuario_id, role_id) VALUES (2, 2);

INSERT INTO tb_categorias(nome) VALUES ('Eletrônicos');
INSERT INTO tb_categorias(nome) VALUES ('Livros');

INSERT INTO tb_produtos (nome, descricao, valor, categoria_id) VALUES ('PC Gamer', 'PC feito para jogos', 4500.00, 1);
INSERT INTO tb_produtos (nome, descricao, valor, categoria_id) VALUES ('O Pequeno Príncipe', 'As aventuras do pequeno príncipe', 200.00, 2);

INSERT INTO tb_carrinhos (valor_total, usuario_id) VALUES (0.00, 2);

INSERT INTO tb_itens_carrinhos (quantidade, valor, carrinho_id, produto_id) VALUES (1, 4500.00, 1, 1);
INSERT INTO tb_itens_carrinhos (quantidade, valor, carrinho_id, produto_id) VALUES (2, 200.00, 1, 2);

INSERT INTO tb_descontos_categorias (tipo, valor, categoria_id) VALUES (2, 10.00, 1);
INSERT INTO tb_descontos_carrinhos (tipo, valor, valor_corte) VALUES (2, 10.00, 1000.00);


