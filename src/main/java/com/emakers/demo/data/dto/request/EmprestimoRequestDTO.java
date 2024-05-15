package com.emakers.demo.data.dto.request;

import com.emakers.demo.data.entity.Livro;
import com.emakers.demo.data.entity.Pessoa;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EmprestimoRequestDTO(
        @NotNull(message = "Livro is required")
        Livro livro,
        @NotNull(message = "Pessoa is required")
        Pessoa pessoa,

        @NotNull(message = "DataEmprestimo is required")
        LocalDate dataEmprestimo,

        @NotNull(message = "DataDevolucao is required")
        LocalDate dataDevolucao,

        @NotNull(message = "Status is required")
        String status

) {
}
