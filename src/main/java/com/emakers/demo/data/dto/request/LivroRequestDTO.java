package com.emakers.demo.data.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record LivroRequestDTO(
        @NotBlank(message = "Nome is required")
        String nome,
        @NotBlank(message = "Autor is required")
        String autor,
        @NotNull(message = "Release date is required")
        @PastOrPresent(message = "Release date must be in the past or present")
        LocalDate datalancamento
) {
}
