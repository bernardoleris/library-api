package com.emakers.demo.data.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EmprestimoRequestDTO(
        @NotNull(message = "idLivro is required", groups = PostValidation.class)
        Long idLivro,
        @NotNull(message = "idPessoa is required", groups = PostValidation.class)
        Long idPessoa,
        @JsonFormat(pattern="dd-MM-yyyy")
        @NotNull(message = "DataDevolucao is required", groups = PostValidation.class)
        @FutureOrPresent(message = "Release date must be in the future or present", groups = PostValidation.class)
        @Schema(example = "30-10-2025")
        LocalDate dataDevolucao
) {
        public interface PostValidation {}
}
