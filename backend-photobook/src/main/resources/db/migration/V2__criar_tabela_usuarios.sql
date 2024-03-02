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
