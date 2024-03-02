CREATE TABLE IF NOT EXISTS publicacoes (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(1000),
    imagem BYTEA,
    curtidas INTEGER,
    usuario_id BIGINT NOT NULL,
    data_de_cadastro DATE NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuarios (id)
);