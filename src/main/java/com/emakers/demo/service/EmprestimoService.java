package com.emakers.demo.service;

import com.emakers.demo.data.dto.request.EmprestimoRequestDTO;
import com.emakers.demo.data.dto.response.EmprestimoResponseDTO;
import com.emakers.demo.data.entity.Emprestimo;
import com.emakers.demo.data.entity.Livro;
import com.emakers.demo.data.entity.Pessoa;
import com.emakers.demo.exceptions.general.EntityNotFoundException;
import com.emakers.demo.repository.EmprestimoRepository;
import com.emakers.demo.repository.LivroRepository;
import com.emakers.demo.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmprestimoService {
    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LivroRepository livroRepository;

    public List<EmprestimoResponseDTO> getAllEmprestimos(){
        List<Emprestimo> emprestimos = emprestimoRepository.findAll();

        return emprestimos.stream().map(EmprestimoResponseDTO::new).collect(Collectors.toList());
    }
    public EmprestimoResponseDTO getEmprestimoById(Long idEmprestimo){
        Emprestimo emprestimo = getEmprestimoEntityById(idEmprestimo);

        return new EmprestimoResponseDTO(emprestimo);
    }

    public EmprestimoResponseDTO createEmprestimo(EmprestimoRequestDTO emprestimoRequestDTO) {
        Livro livro = livroRepository.findById(emprestimoRequestDTO.idLivro()).orElseThrow(()-> new RuntimeException("Livro was not find"));
        Pessoa pessoa = pessoaRepository.findById(emprestimoRequestDTO.idPessoa()).orElseThrow(()-> new RuntimeException("Pessoa was not find"));
        Emprestimo emprestimo = new Emprestimo(emprestimoRequestDTO, livro, pessoa);
        emprestimoRepository.save(emprestimo);

        return new EmprestimoResponseDTO(emprestimo);
    }
    // Método para atualizar o emprestimo, só permite atualizar as datas de emprestimo e devolução.
    public EmprestimoResponseDTO updateDate(long idEmprestimo, EmprestimoRequestDTO emprestimoRequestDTO){
        Emprestimo emprestimo = getEmprestimoEntityById(idEmprestimo);
        emprestimo.setDataEmprestimo(emprestimoRequestDTO.dataEmprestimo());
        emprestimo.setDataDevolucao(emprestimoRequestDTO.dataDevolucao());
        emprestimoRepository.save(emprestimo);

        return new EmprestimoResponseDTO(emprestimo);
    }

    public String deleteEmprestimo(long idEmprestimo){
        Emprestimo emprestimo = getEmprestimoEntityById(idEmprestimo);
        emprestimoRepository.delete(emprestimo);

        return "Emprestimo id:" + idEmprestimo + " deleted!";
    }

    private Emprestimo getEmprestimoEntityById(Long idEmprestimo){
        return emprestimoRepository.findById(idEmprestimo).orElseThrow(()-> new EntityNotFoundException(idEmprestimo));
    }

    // Método para atualizar o status do emprestimo para "DEVOLVIDO".
    public EmprestimoResponseDTO returnedBook(long idEmprestimo){
        Emprestimo emprestimo = getEmprestimoEntityById(idEmprestimo);

        emprestimo.updateStatus("DEVOLVIDO");

        emprestimoRepository.save(emprestimo);
        return new EmprestimoResponseDTO(emprestimo);
    }
}
