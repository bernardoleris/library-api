package com.emakers.demo.data.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record LivroRequestDTO(
        @NotBlank(message = "Nome is required")
        String nome,
        @NotBlank(message = "Autor is required")
        String autor,
        @JsonFormat(pattern="dd-MM-yyyy")
        @NotNull(message = "Release date is required")
        @PastOrPresent(message = "Release date must be in the past or present")
        LocalDate dataLancamento
) {
}
