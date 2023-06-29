package com.backend.backendtcc.controller;

import com.backend.backendtcc.dto.EmpresaDTO;
import com.backend.backendtcc.service.EmpresaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {
    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    public ResponseEntity<List<EmpresaDTO>> listarEmpresas() {
        List<EmpresaDTO> empresas = empresaService.listarEmpresas();
        return ResponseEntity.ok(empresas);
    }

    @GetMapping("/{idEmpresa}")
    public ResponseEntity<EmpresaDTO> obterEmpresa(@PathVariable int idEmpresa) {
        EmpresaDTO empresa = empresaService.obterEmpresa(idEmpresa);
        if (empresa != null) {
            return ResponseEntity.ok(empresa);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<EmpresaDTO> criarEmpresa(@RequestBody EmpresaDTO empresaDTO) {
        EmpresaDTO novaEmpresa = empresaService.criarEmpresa(empresaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaEmpresa);
    }

    @PutMapping("/{idEmpresa}")
    public ResponseEntity<EmpresaDTO> atualizarEmpresa(@PathVariable int idEmpresa, @RequestBody EmpresaDTO empresaDTO) {
        EmpresaDTO empresaAtualizada = empresaService.atualizarEmpresa(idEmpresa, empresaDTO);
        if (empresaAtualizada != null) {
            return ResponseEntity.ok(empresaAtualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idEmpresa}")
    public ResponseEntity<Void> deletarEmpresa(@PathVariable int idEmpresa) {
        empresaService.deletarEmpresa(idEmpresa);
        return ResponseEntity.noContent().build();
    }
}
