package com.emakers.demo.data.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PessoaRequestDTO(
        @NotBlank(message = "Nome is required")
        String nome,
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP must to be in the format 'XXXX-XXX'")
        String cep
) {
}
