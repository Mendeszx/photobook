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

CREATE TABLE IF NOT EXISTS publicacoes
(
    id               SERIAL PRIMARY KEY,
    descricao        VARCHAR(1000),
    imagem           BYTEA,
    curtidas         INTEGER,
    usuario_id       BIGINT NOT NULL,
    data_de_cadastro DATE   NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuarios (id)
);

CREATE TABLE IF NOT EXISTS comentarios
(
    id               SERIAL PRIMARY KEY,
    publicacao_id    BIGINT        NOT NULL,
    usuario_id       BIGINT        NOT NULL,
    comentario       VARCHAR(1000) NOT NULL,
    data_de_cadastro DATE          NOT NULL,
    FOREIGN KEY (publicacao_id) REFERENCES publicacoes (id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios (id)
);

CREATE TABLE IF NOT EXISTS albuns
(
    id               SERIAL PRIMARY KEY,
    descricao        VARCHAR(1000),
    curtidas         INTEGER,
    usuario_id       BIGINT NOT NULL,
    data_de_cadastro DATE,
    FOREIGN KEY (usuario_id) REFERENCES usuarios (id)
);

CREATE TABLE imagens_albuns
(
    id       SERIAL PRIMARY KEY,
    imagem   BYTEA,
    album_id BIGINT NOT NULL,
    FOREIGN KEY (album_id) REFERENCES albuns (id)
);