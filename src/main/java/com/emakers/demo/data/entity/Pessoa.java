package com.emakers.demo.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Pessoa")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPessoa;

    @Column(name = "nome", nullable = false, length = 80)
    private String nome;

    @Column(name = "cep", nullable = false, length = 9)
    @Size(min = 9, max = 9, message = "O CEP deve ter exatamente 9 caracteres")
    private String cep;
}
