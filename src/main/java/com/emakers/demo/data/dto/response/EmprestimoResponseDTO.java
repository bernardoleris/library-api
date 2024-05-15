package com.emakers.demo.data.dto.response;

import com.emakers.demo.data.entity.Emprestimo;
import com.emakers.demo.data.entity.Livro;
import com.emakers.demo.data.entity.Pessoa;

public record EmprestimoResponseDTO(
        Long id,
        Livro livro,
        Pessoa pessoa
) {
    public EmprestimoResponseDTO (Emprestimo emprestimo){
        this(emprestimo.getIdEmprestimo(),emprestimo.getLivro(), emprestimo.getPessoa());
    }
}
