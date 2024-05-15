package com.emakers.demo.service;

import com.emakers.demo.data.dto.request.LivroRequestDTO;
import com.emakers.demo.data.dto.response.LivroResponseDTO;
import com.emakers.demo.data.dto.response.LivroResponseDTO;
import com.emakers.demo.data.entity.Livro;
import com.emakers.demo.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    public List<LivroResponseDTO> getAllLivros(){
        List<Livro> livros = livroRepository.findAll();

        return livros.stream().map(LivroResponseDTO::new).collect(Collectors.toList());
    }

    public LivroResponseDTO getLivroById(Long idLivro){
        Livro livro = getLivroEntityById(idLivro);

        return new LivroResponseDTO(livro);
    }

    public LivroResponseDTO createLivro(LivroRequestDTO livroRequestDTO){
        Livro livro = new Livro(livroRequestDTO);
        livroRepository.save(livro);

        return new LivroResponseDTO(livro);
    }

    public LivroResponseDTO updateLivro(Long idLivro, LivroRequestDTO livroRequestDTO){
        Livro livro = getLivroEntityById(idLivro);
        livro.setAutor(livroRequestDTO.autor());
        livro.setNome(livroRequestDTO.nome());
        livro.setDataLancamento(livroRequestDTO.datalancamento());
        livroRepository.save(livro);

        return new LivroResponseDTO(livro);
    }

    public String deleteLivro(Long idLivro){
        Livro livro = getLivroEntityById(idLivro);
        livroRepository.delete(livro);

        return "Livro id:" + idLivro + " deleted!";
    }

    private Livro getLivroEntityById(Long idLivro){
        return livroRepository.findById(idLivro).orElseThrow(()-> new RuntimeException("Livro was not find"));
    }
}
