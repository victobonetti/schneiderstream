CREATE TABLE postagemcomentarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    postagem_id INT NOT NULL,
    conteudo TEXT NOT NULL
);