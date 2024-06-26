package com.emakers.demo.data.entity;

import com.emakers.demo.data.dto.request.LivroRequestDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "Livro")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivro;

    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @Column(name = "autor", nullable = false, length = 45)
    private String autor;

    @Column(name = "dataLancamento", nullable = false)
    private LocalDate dataLancamento;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "quantidadeEmprestimos")
    private int quantidadeEmprestimos;

    @Builder
    public Livro(LivroRequestDTO livroRequestDTO){
        this.nome = livroRequestDTO.nome();
        this.autor = livroRequestDTO.autor();
        this.dataLancamento = livroRequestDTO.dataLancamento();
        this.status = "DISPONÍVEL";
        this.quantidadeEmprestimos = 0;
    }
}