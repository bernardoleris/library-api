package com.emakers.demo.data.dto.request;

import com.emakers.demo.data.entity.Livro;
import com.emakers.demo.data.entity.Pessoa;
import jakarta.validation.constraints.NotNull;

public record EmprestimoRequestDTO(
        @NotNull(message = "Livro is required")
        Livro livro,
        @NotNull(message = "Pessoa is required")
        Pessoa pessoa
) {
}
