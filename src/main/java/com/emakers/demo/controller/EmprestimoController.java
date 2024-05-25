package com.emakers.demo.controller;

import com.emakers.demo.data.dto.request.EmprestimoRequestDTO;
import com.emakers.demo.data.dto.response.EmprestimoResponseDTO;
import com.emakers.demo.data.dto.response.LivroResponseDTO;
import com.emakers.demo.service.EmprestimoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimo")
@Tag(name = "Emprestimo", description = "Endpoints related to the Emprestimo area")
public class EmprestimoController {
    @Autowired
    private EmprestimoService emprestimoService;

    @Operation(
            summary = "Retrieve a list of registered Emprestimos.",
            description = "Returns a list of Emprestimos already registered in the system.",
            tags = {"Emprestimo"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EmprestimoResponseDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<EmprestimoResponseDTO>> getAllEmprestimos() {
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.getAllEmprestimos());
    }

    @Operation(
            summary = "Retrieve an Emprestimo by its ID.",
            description = "Fetches the details of a registered Emprestimo based on the provided Emprestimo ID.",
            tags = {"Emprestimo"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EmprestimoResponseDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    @GetMapping(value = "/{idEmprestimo}", produces = "application/json")
    public ResponseEntity<EmprestimoResponseDTO> getEmprestimoById(@PathVariable long idEmprestimo) {
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.getEmprestimoById(idEmprestimo));
    }

    @Operation(
            summary = "Create a new Emprestimo.",
            description = "Registers a new Emprestimo in the system based on the provided details.",
            tags = {"Emprestimo"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EmprestimoResponseDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    @PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
    public ResponseEntity<EmprestimoResponseDTO> createEmprestimo(@Validated(EmprestimoRequestDTO.PostValidation.class) @RequestBody EmprestimoRequestDTO emprestimoRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.createEmprestimo(emprestimoRequestDTO));
    }

    @Operation(
            summary = "Update the return date of an Emprestimo.",
            description = "Updates the return date of an existing Emprestimo based on the provided Emprestimo ID and new date details.",
            tags = {"Emprestimo"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EmprestimoResponseDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<EmprestimoResponseDTO> updateDate(@PathVariable Long idEmprestimo, @Valid @RequestBody EmprestimoRequestDTO emprestimoRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.updateDate(idEmprestimo, emprestimoRequestDTO));
    }

    @Operation(
            summary = "Delete an Emprestimo.",
            description = "Marks an existing Emprestimo as deleted based on the provided Emprestimo ID.",
            tags = {"Emprestimo"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = String.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    @DeleteMapping(value = "/return/{idEmprestimo}")
    public ResponseEntity<String> returnEmprestimo(@PathVariable long idEmprestimo) {
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.returnEmprestimo(idEmprestimo));
    }
}

