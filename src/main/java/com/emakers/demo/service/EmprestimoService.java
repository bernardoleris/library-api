package com.emakers.demo.service;

import com.emakers.demo.data.dto.response.EmprestimoResponseDTO;
import com.emakers.demo.data.dto.response.LivroResponseDTO;
import com.emakers.demo.data.entity.Emprestimo;
import com.emakers.demo.data.entity.Livro;
import com.emakers.demo.data.entity.Pessoa;
import com.emakers.demo.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmprestimoService {
    @Autowired
    private EmprestimoRepository emprestimoRepository;

    public EmprestimoResponseDTO getLivroEmprestadoById(Long idEmprestimo){
        Emprestimo emprestimo = getEmprestimoEntityById(idEmprestimo);

        return new EmprestimoResponseDTO(emprestimo);
    }

    private Emprestimo getEmprestimoEntityById(Long idEmprestimo){
        return emprestimoRepository.findById(idEmprestimo).orElseThrow(()-> new RuntimeException("Emprestimo was not find"));
    }
}
