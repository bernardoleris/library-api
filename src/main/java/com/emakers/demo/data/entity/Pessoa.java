package com.emakers.demo.data.entity;

import com.emakers.demo.data.dto.request.PessoaRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "Pessoa")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPessoa;

    @Column(name = "nome", nullable = false, length = 80)
    private String nome;

    @Column(name = "cep", nullable = false, length = 9)
    @Size(min = 9, max = 9, message = "O CEP deve ter exatamente 9 caracteres")
    private String cep;
    @Builder
    public Pessoa(PessoaRequestDTO pessoaRequestDTO){
        this.nome = pessoaRequestDTO.nome();
        this.cep = pessoaRequestDTO.cep();
    }
}
