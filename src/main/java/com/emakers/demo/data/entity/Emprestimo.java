package com.emakers.demo.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Emprestimo")
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEmprestimo;

    @ManyToOne()
    @JoinColumn(name = "idLivro")
    private Livro livro;

    @ManyToOne()
    @JoinColumn(name = "idPessoa")
    private Pessoa pessoa;
}
