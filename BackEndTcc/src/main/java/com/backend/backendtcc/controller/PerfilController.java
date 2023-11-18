package com.backend.backendtcc.controller;

import com.backend.backendtcc.dto.PerfilDTO;
import com.backend.backendtcc.service.PerfilService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfils")
public class PerfilController {
    private final PerfilService perfilService;

    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @GetMapping
    public ResponseEntity<List<PerfilDTO>> listarPerfils() {
        List<PerfilDTO> perfils = perfilService.listarPerfils();
        return ResponseEntity.ok(perfils);
    }

    @GetMapping("/{idPerfil}")
    public ResponseEntity<PerfilDTO> obterPerfil(@PathVariable int idPerfil) {
        PerfilDTO perfil = perfilService.obterPerfil(idPerfil);
        if (perfil != null) {
            return ResponseEntity.ok(perfil);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PerfilDTO> criarPerfil(@RequestBody PerfilDTO perfilDTO) {
        PerfilDTO novaPerfil = perfilService.criarPerfil(perfilDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPerfil);
    }

    @PutMapping("/{idPerfil}")
    public ResponseEntity<PerfilDTO> atualizarPerfil(@PathVariable int idPerfil, @RequestBody PerfilDTO perfilDTO) {
        PerfilDTO perfilAtualizada = perfilService.atualizarPerfil(idPerfil, perfilDTO);
        if (perfilAtualizada != null) {
            return ResponseEntity.ok(perfilAtualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idPerfil}")
    public ResponseEntity<Void> deletarPerfil(@PathVariable int idPerfil) {
        perfilService.deletarPerfil(idPerfil);
        return ResponseEntity.noContent().build();
    }
}
