package com.emakers.demo.service;

import com.emakers.demo.data.dto.request.EmprestimoRequestDTO;
import com.emakers.demo.data.dto.response.EmprestimoResponseDTO;
import com.emakers.demo.data.entity.Emprestimo;
import com.emakers.demo.data.entity.Livro;
import com.emakers.demo.data.entity.Pessoa;
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

    public EmprestimoResponseDTO createEmprestimo(EmprestimoRequestDTO emprestimoRequestDTO) {
        // Buscar Livro pelo ID
        Livro livro = livroRepository.findById(emprestimoRequestDTO.idLivro()).orElseThrow(()-> new RuntimeException("Livro was not find"));

        // Buscar Pessoa pelo ID
        Pessoa pessoa = pessoaRepository.findById(emprestimoRequestDTO.idPessoa()).orElseThrow(()-> new RuntimeException("Pessoa was not find"));

        // Criar Emprestimo
        Emprestimo emprestimo = new Emprestimo(emprestimoRequestDTO, livro, pessoa);
        emprestimo.setLivro(livro);
        emprestimo.setPessoa(pessoa);
        emprestimo.setDataEmprestimo(emprestimoRequestDTO.dataEmprestimo());
        emprestimo.setDataDevolucao(emprestimoRequestDTO.dataDevolucao());
        emprestimo.setStatus(emprestimoRequestDTO.status());

        // Salvar Emprestimo
        Emprestimo emprestimoSalvo = emprestimoRepository.save(emprestimo);
        return new EmprestimoResponseDTO(emprestimoSalvo);
    }

    public EmprestimoResponseDTO updateEmprestimo(long idEmprestimo, EmprestimoRequestDTO emprestimoRequestDTO){
        Emprestimo emprestimo = getEmprestimoEntityById(idEmprestimo);
        emprestimo.setDataEmprestimo(emprestimoRequestDTO.dataEmprestimo());
        emprestimo.setDataDevolucao(emprestimoRequestDTO.dataDevolucao());
        emprestimo.setStatus(emprestimoRequestDTO.status());

        return new EmprestimoResponseDTO(emprestimo);
    }

    public String deleteEmprestimo(long idEmprestimo){
        Emprestimo emprestimo = getEmprestimoEntityById(idEmprestimo);
        emprestimoRepository.delete(emprestimo);

        return "Emprestimo id:" + idEmprestimo + " deleted!";
    }

    private Emprestimo getEmprestimoEntityById(Long idEmprestimo){
        return emprestimoRepository.findById(idEmprestimo).orElseThrow(()-> new RuntimeException("Emprestimo was not find"));
    }
}
