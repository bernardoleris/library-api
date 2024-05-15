package com.emakers.demo.data.entity;

import com.emakers.demo.data.dto.request.EmprestimoRequestDTO;
import jakarta.persistence.*;
import lombok.Builder;
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
    @Builder
    public Emprestimo(EmprestimoRequestDTO emprestimoRequestDTO){
        this.livro = emprestimoRequestDTO.livro();
        this.pessoa = emprestimoRequestDTO.pessoa();
    }
}
