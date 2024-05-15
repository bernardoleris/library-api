package com.emakers.demo.data.dto.response;

import com.emakers.demo.data.entity.Pessoa;

public record PessoaResponseDTO(
        Long id,
        String nome,
        String cep
) {
    public PessoaResponseDTO(Pessoa pessoa){
        this(pessoa.getIdPessoa(), pessoa.getNome(), pessoa.getCep());
    }
}
