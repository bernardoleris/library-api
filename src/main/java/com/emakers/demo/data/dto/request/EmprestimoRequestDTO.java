package com.emakers.demo.data.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EmprestimoRequestDTO(
        @NotNull(message = "idLivro is required", groups = PostValidation.class)
        Long idLivro,
        @NotNull(message = "idPessoa is required", groups = PostValidation.class)
        Long idPessoa,
        @JsonFormat(pattern="dd-MM-yyyy")
        @NotNull(message = "DataEmprestimo is required", groups = PostValidation.class)
        LocalDate dataEmprestimo,
        @JsonFormat(pattern="dd-MM-yyyy")
        @NotNull(message = "DataDevolucao is required", groups = PostValidation.class)
        LocalDate dataDevolucao,
        String status
) {
        public interface PostValidation {}
}
