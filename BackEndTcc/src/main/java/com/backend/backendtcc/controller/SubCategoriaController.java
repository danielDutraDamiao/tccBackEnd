package com.backend.backendtcc.controller;

import com.backend.backendtcc.dto.SubCategoriaDTO;
import com.backend.backendtcc.service.SubCategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subcategorias")
public class SubCategoriaController {
    private final SubCategoriaService subCategoriaService;

    public SubCategoriaController(SubCategoriaService subCategoriaService) {
        this.subCategoriaService = subCategoriaService;
    }

    @GetMapping
    public ResponseEntity<List<SubCategoriaDTO>> listarSubCategorias() {
        List<SubCategoriaDTO> subCategorias = subCategoriaService.listarSubCategorias();
        return ResponseEntity.ok(subCategorias);
    }

    @GetMapping("/{idSubCategoria}")
    public ResponseEntity<SubCategoriaDTO> obterSubCategoria(@PathVariable int idSubCategoria) {
        SubCategoriaDTO subCategoria = subCategoriaService.obterSubCategoria(idSubCategoria);
        if (subCategoria != null) {
            return ResponseEntity.ok(subCategoria);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<SubCategoriaDTO> criarSubCategoria(@RequestBody SubCategoriaDTO subCategoriaDTO) {
        SubCategoriaDTO novaSubCategoria = subCategoriaService.criarSubCategoria(subCategoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaSubCategoria);
    }

    @PutMapping("/{idSubCategoria}")
    public ResponseEntity<SubCategoriaDTO> atualizarSubCategoria(@PathVariable int idSubCategoria, @RequestBody SubCategoriaDTO subCategoriaDTO) {
        SubCategoriaDTO subCategoriaAtualizada = subCategoriaService.atualizarSubCategoria(idSubCategoria, subCategoriaDTO);
        if (subCategoriaAtualizada != null) {
            return ResponseEntity.ok(subCategoriaAtualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idSubCategoria}")
    public ResponseEntity<Void> deletarSubCategoria(@PathVariable int idSubCategoria) {
        subCategoriaService.deletarSubCategoria(idSubCategoria);
        return ResponseEntity.noContent().build();
    }
}