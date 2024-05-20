package com.emakers.demo.controller;

import com.emakers.demo.data.dto.request.EmprestimoRequestDTO;
import com.emakers.demo.data.dto.response.EmprestimoResponseDTO;
import com.emakers.demo.service.EmprestimoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {
    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<EmprestimoResponseDTO>> getAllEmprestimos() {
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.getAllEmprestimos());
    }

    @GetMapping(value = "/{idEmprestimo}")
    public ResponseEntity<EmprestimoResponseDTO> getEmprestimoById(@PathVariable long idEmprestimo) {
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.getEmprestimoById(idEmprestimo));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<EmprestimoResponseDTO> createEmprestimo(@Validated(EmprestimoRequestDTO.PostValidation.class) @RequestBody EmprestimoRequestDTO emprestimoRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.createEmprestimo(emprestimoRequestDTO));
    }

    @PatchMapping (value = "/updateDate/{idEmprestimo}")
    public ResponseEntity<EmprestimoResponseDTO> updateDate(@PathVariable Long idEmprestimo, @Valid @RequestBody EmprestimoRequestDTO emprestimoRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.updateDate(idEmprestimo, emprestimoRequestDTO));
    }

    @DeleteMapping(value = "/delete/{idEmprestimo}")
    public ResponseEntity<String> deleteEmprestimo(@PathVariable long idEmprestimo) {
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.deleteEmprestimo(idEmprestimo));
    }

    @PatchMapping (value = "/returned/{idEmprestimo}")
    public ResponseEntity<EmprestimoResponseDTO> returnedBook(@PathVariable Long idEmprestimo, @Valid @RequestBody EmprestimoRequestDTO emprestimoRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.returnedBook(idEmprestimo));
    }
}

