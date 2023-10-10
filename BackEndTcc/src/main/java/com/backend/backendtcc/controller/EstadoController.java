package com.backend.backendtcc.controller;

import com.backend.backendtcc.model.Estado;
import com.backend.backendtcc.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public List<Estado> findAll() {
        return estadoService.findAll();
    }

    @GetMapping("/{id}")
    public Estado findById(@PathVariable int id) {
        return estadoService.findById(id);
    }

    @PostMapping
    public Estado save(@RequestBody Estado estado) {
        return estadoService.save(estado);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        estadoService.delete(id);
    }
}
