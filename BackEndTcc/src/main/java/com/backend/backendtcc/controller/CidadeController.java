package com.backend.backendtcc.controller;

import com.backend.backendtcc.dto.CidadeDTO;
import com.backend.backendtcc.model.Cidade;
import com.backend.backendtcc.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public List<CidadeDTO> findAll() {
        return cidadeService.findAll();
    }

    @GetMapping("/{id}")
    public CidadeDTO findById(@PathVariable int id) {
        return cidadeService.findById(id);
    }


    @PostMapping
    public Cidade save(@RequestBody Cidade cidade) {
        return cidadeService.save(cidade);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        cidadeService.delete(id);
    }
}
