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

    // Quando um empréstimo é criado a quantidade de emprestimos que do livro envolvido é aumentado em 1.
    public EmprestimoResponseDTO createEmprestimo(EmprestimoRequestDTO emprestimoRequestDTO) {
        Livro livro = livroRepository.findById(emprestimoRequestDTO.idLivro()).orElseThrow(()-> new RuntimeException("Livro was not find"));
        livro.setQuantidadeEmprestimos(livro.getQuantidadeEmprestimos()+1);
        livroRepository.save(livro);
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
    // Método para devolução do livro (É necessário apagar o Emprestimo do banco de dados para que o livro possa ser emprestado para outra pessoa).
    public String returnEmprestimo(long idEmprestimo){
        Emprestimo emprestimo = getEmprestimoEntityById(idEmprestimo);
        emprestimoRepository.delete(emprestimo);

        return "Emprestimo ID:" + idEmprestimo + " returned!";
    }

    private Emprestimo getEmprestimoEntityById(Long idEmprestimo){
        return emprestimoRepository.findById(idEmprestimo).orElseThrow(()-> new EntityNotFoundException(idEmprestimo));
    }

}
