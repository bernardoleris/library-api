package com.emakers.demo.data.dto.response;

import com.emakers.demo.data.entity.Emprestimo;
import com.emakers.demo.data.entity.Livro;
import com.emakers.demo.data.entity.Pessoa;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record EmprestimoResponseDTO(
        Long id,
        Livro idLivro,
        Pessoa idPessoa,
        @JsonFormat(pattern="dd-MM-yyyy")
        LocalDate dataEmprestimo,
        @JsonFormat(pattern="dd-MM-yyyy")
        LocalDate dataDevolucao,
        String status
) {
    public EmprestimoResponseDTO (Emprestimo emprestimo){
        this(emprestimo.getIdEmprestimo(),(emprestimo.getLivro()), emprestimo.getPessoa(), emprestimo.getDataEmprestimo(), emprestimo.getDataDevolucao(), emprestimo.getStatus());
    }
}
