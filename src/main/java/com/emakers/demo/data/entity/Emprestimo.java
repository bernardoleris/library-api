package com.emakers.demo.data.entity;

import com.emakers.demo.data.dto.request.EmprestimoRequestDTO;
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
@Table(name = "Emprestimo")
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmprestimo;

    @ManyToOne()
    @JoinColumn(name = "idLivro", referencedColumnName = "idLivro")
    private Livro livro;

    @ManyToOne()
    @JoinColumn(name = "idPessoa", referencedColumnName = "idPessoa")
    private Pessoa pessoa;

    @Column(name = "dataEmprestimo")
    private LocalDate dataEmprestimo;

    @Column(name = "dataDevolucao")
    private LocalDate dataDevolucao;
    @Builder
    public Emprestimo(EmprestimoRequestDTO emprestimoRequestDTO, Livro livro, Pessoa pessoa){
        this.livro = livro;
        this.pessoa = pessoa;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = emprestimoRequestDTO.dataDevolucao();
    }
}
