-- Create the pessoa table
CREATE TABLE IF NOT EXISTS pessoa (
    id_pessoa SERIAL PRIMARY KEY,
    nome VARCHAR(80) NOT NULL,
    cep VARCHAR(9) NOT NULL
);

-- Create the livro table
CREATE TABLE IF NOT EXISTS livro (
    id_livro SERIAL PRIMARY KEY,
    nome VARCHAR(45) NOT NULL,
    autor VARCHAR(45) NOT NULL,
    data_lancamento DATE,
    status VARCHAR(45) NOT NULL DEFAULT 'DISPONÍVEL',
    quantidade_emprestimos INT NOT NULL DEFAULT 0
);

-- Create the emprestimo table with the necessary columns and foreign key constraints
CREATE TABLE IF NOT EXISTS emprestimo (
    id_emprestimo SERIAL PRIMARY KEY,
    id_livro INT NOT NULL,
    id_pessoa INT NOT NULL,
    data_emprestimo DATE,
    data_devolucao DATE,
    FOREIGN KEY (id_livro) REFERENCES livro(id_livro),
    FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa)
);
