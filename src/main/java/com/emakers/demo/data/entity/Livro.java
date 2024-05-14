package com.emakers.demo.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Livro")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idLivro;

    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @Column(name = "autor", nullable = false, length = 45)
    private String autor;

    @Column(name = "dataLancamento", nullable = false)
    private LocalDate dataLancamento;
}