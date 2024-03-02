CREATE TABLE IF NOT EXISTS usuarios
(
    id               SERIAL PRIMARY KEY,
    nome             VARCHAR(255) NOT NULL,
    foto             BYTEA,
    email            VARCHAR(255) NOT NULL,
    senha            VARCHAR(255) NOT NULL,
    data_de_cadastro DATE         NOT NULL,
    role             VARCHAR(50)  NOT NULL,
    usuario_ativo    BOOLEAN      NOT NULL
);

INSERT INTO usuarios (nome, foto, email, senha, data_de_cadastro, "role", usuario_ativo)
VALUES ('Guilherme Mendes', NULL, 'guilherme@email.com', '$2a$10$jWR6otIiQZCk9GS2SJiqNeFUKVz91gHxnSeS90BP2WvMr0mUFI/dO',
        CURRENT_DATE, 'ROLE_USUARIO', true);
