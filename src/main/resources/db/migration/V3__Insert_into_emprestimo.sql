-- Insert initial data into emprestimo table
INSERT INTO emprestimo (id_livro, id_pessoa, data_emprestimo, data_devolucao, status) VALUES
(1, 1, TO_DATE('01-05-2024', 'DD-MM-YYYY'), TO_DATE('15-05-2024', 'DD-MM-YYYY'), 'EMPRESTADO'),
(2, 2, TO_DATE('05-05-2024', 'DD-MM-YYYY'), TO_DATE('20-05-2024', 'DD-MM-YYYY'), 'EMPRESTADO'),
(3, 3, TO_DATE('10-05-2024', 'DD-MM-YYYY'), TO_DATE('25-05-2024', 'DD-MM-YYYY'), 'EMPRESTADO');


