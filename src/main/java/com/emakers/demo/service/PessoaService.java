package com.emakers.demo.service;

import com.emakers.demo.data.dto.request.PessoaRequestDTO;
import com.emakers.demo.data.dto.response.PessoaResponseDTO;
import com.emakers.demo.data.entity.Pessoa;
import com.emakers.demo.exceptions.general.EntityNotFoundException;
import com.emakers.demo.exceptions.general.EntityReferencedException;
import com.emakers.demo.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;
    public List<PessoaResponseDTO> getAllPessoas(){
        List<Pessoa> pessoas = pessoaRepository.findAll();

        return pessoas.stream().map(PessoaResponseDTO::new).collect(Collectors.toList());
    }
    public PessoaResponseDTO getPessoaById(Long idPessoa){
        Pessoa pessoa = getPessoaEntityById(idPessoa);

        return new PessoaResponseDTO(pessoa);
    }

    public PessoaResponseDTO createPessoa(PessoaRequestDTO pessoaRequestDTO){
        Pessoa pessoa = new Pessoa(pessoaRequestDTO);
        pessoaRepository.save(pessoa);

        return new PessoaResponseDTO(pessoa);
    }

    public PessoaResponseDTO updatePessoa(Long idPessoa, PessoaRequestDTO pessoaRequestDTO){
        Pessoa pessoa = getPessoaEntityById(idPessoa);
        pessoa.setCep(pessoaRequestDTO.cep());
        pessoa.setNome(pessoaRequestDTO.nome());
        pessoaRepository.save(pessoa);

        return new PessoaResponseDTO(pessoa);
    }

    public String deletePessoa(Long idPessoa){
        try{
            Pessoa pessoa = getPessoaEntityById(idPessoa);
            pessoaRepository.delete(pessoa);
            return "Pessoa id:" + idPessoa + " deleted!";
        }catch (DataIntegrityViolationException e) {
            throw new EntityReferencedException("Pessoa with ID " + idPessoa + " is referenced by an existing Emprestimo");
        }
    }

    private Pessoa getPessoaEntityById(Long idPessoa){
        return pessoaRepository.findById(idPessoa).orElseThrow(()-> new EntityNotFoundException(idPessoa));
    }
}
