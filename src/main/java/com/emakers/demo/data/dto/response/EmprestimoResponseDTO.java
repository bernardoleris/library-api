package com.emakers.demo.data.dto.response;

import com.emakers.demo.data.entity.Emprestimo;
import com.emakers.demo.data.entity.Livro;
import com.emakers.demo.data.entity.Pessoa;

import java.time.LocalDate;

public record EmprestimoResponseDTO(
        Long id,
        Livro livro,
        Pessoa pessoa,
        LocalDate dataEmprestimo,
        LocalDate dataDevolucao,
        String status
) {
    public EmprestimoResponseDTO (Emprestimo emprestimo){
        this(emprestimo.getIdEmprestimo(),emprestimo.getLivro(), emprestimo.getPessoa(), emprestimo.getDataEmprestimo(), emprestimo.getDataDevolucao(), emprestimo.getStatus());
    }
}
