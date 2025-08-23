CREATE TABLE IF NOT EXISTS clientes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);
INSERT INTO clientes (nome) VALUES ('Teste');