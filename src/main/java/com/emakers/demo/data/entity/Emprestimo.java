package com.emakers.demo.data.entity;

import com.emakers.demo.data.dto.request.EmprestimoRequestDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Emprestimo")
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEmprestimo;

    @ManyToOne()
    @JoinColumn(name = "idLivros")
    private Livro livro;

    @ManyToOne()
    @JoinColumn(name = "idPessoa")
    private Pessoa pessoa;

    @Column(name = "dataEmprestimo")
    private LocalDate dataEmprestimo;

    @Column(name = "dataDevolucao")
    private LocalDate dataDevolucao;

    @Column(name = "status")
    private String status;
    @Builder
    public Emprestimo(EmprestimoRequestDTO emprestimoRequestDTO){
        this.livro = emprestimoRequestDTO.livro();
        this.pessoa = emprestimoRequestDTO.pessoa();
        this.dataEmprestimo = emprestimoRequestDTO.dataEmprestimo();
        this.dataDevolucao = emprestimoRequestDTO.dataDevolucao();
        this.status = emprestimoRequestDTO.status();
    }
}
