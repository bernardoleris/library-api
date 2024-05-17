package com.emakers.demo.data.dto.request;

import com.emakers.demo.data.entity.Livro;
import com.emakers.demo.data.entity.Pessoa;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EmprestimoRequestDTO(
        @NotNull(message = "idLivro is required")
        Long idLivro,
        @NotNull(message = "idPessoa is required")
        Long idPessoa,

        @JsonFormat(pattern="dd-MM-yyyy")
        @NotNull(message = "DataEmprestimo is required")
        LocalDate dataEmprestimo,

        @JsonFormat(pattern="dd-MM-yyyy")
        @NotNull(message = "DataDevolucao is required")
        LocalDate dataDevolucao,

        @NotNull(message = "Status is required")
        String status

) {
}
