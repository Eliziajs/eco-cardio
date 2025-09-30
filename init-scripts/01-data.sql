-- init-scripts/01-data.sql
INSERT INTO users (name, lastname, genero, telefone, email, password, crm, cpf, data) VALUES
('João', 'Silva', 'MASCULINO', '(11) 99999-1111', 'joao.silva@email.com', '$2a$10$XPLOR8hIn6A5qB7zQKTw.e5v6JkMq1YdN7G2HpL9mR3sVtUwW1xYz', 'CRM/SP 123456', '123.456.789-00', CURRENT_TIMESTAMP),
('Maria', 'Santos', 'FEMININO', '(21) 98888-2222', 'maria.santos@email.com', '$2a$10$AbCdEfGhIjKlMnOpQrStUvWxYzAbCdEfGhIjKlMnOpQrStUvWx', 'CRM/RJ 654321', '987.654.321-00', CURRENT_TIMESTAMP);
