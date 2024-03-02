INSERT INTO usuarios (nome, foto, email, senha, data_de_cadastro, "role", usuario_ativo)
VALUES ('Guilherme Mendes', NULL, 'guilherme@email.com', '$2a$10$jWR6otIiQZCk9GS2SJiqNeFUKVz91gHxnSeS90BP2WvMr0mUFI/dO',
        CURRENT_DATE, 'ROLE_USUARIO', true);

INSERT INTO usuarios (nome, foto, email, senha, data_de_cadastro, "role", usuario_ativo)
VALUES ('Milena Galende', NULL, 'milena@email.com', '$2a$10$uZX.C7jE7j6PV2QDsAH0e.xxSu.R5q/GWzbFa/CCvSWKG.hH4XUKK',
        CURRENT_DATE, 'ROLE_USUARIO', true);

INSERT INTO publicacoes (descricao, imagem, curtidas, usuario_id, data_de_cadastro)
VALUES ('Minha gatinha, Hana!', decode(
        'hex'), 0, 1, CURRENT_DATE);

INSERT INTO comentarios (publicacao_id, usuario_id, comentario, data_de_cadastro)
VALUES (1, 2, 'Que linda!', CURRENT_DATE);