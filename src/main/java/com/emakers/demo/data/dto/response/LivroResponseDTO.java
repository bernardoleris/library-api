package com.emakers.demo.data.dto.response;

import com.emakers.demo.data.entity.Livro;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record LivroResponseDTO(
        Long id,
        String nome,
        String autor,
        @JsonFormat(pattern="dd-MM-yyyy")
        LocalDate dataLancamento,
        int quantidadeEmprestimos,
        String status
) {
    public LivroResponseDTO(Livro livro){
        this(livro.getIdLivro(), livro.getNome(), livro.getAutor(), livro.getDataLancamento(), livro.getQuantidadeEmprestimos(), livro.getStatus());
    }
}
