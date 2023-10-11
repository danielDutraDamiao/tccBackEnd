package com.backend.backendtcc.controller;

import com.backend.backendtcc.model.Ong;
import com.backend.backendtcc.service.OngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ongs")
public class OngController {

    @Autowired
    private OngService ongService;

    @GetMapping
    public List<Ong> findAll() {
        return ongService.findAll();
    }

    @GetMapping("/{id}")
    public Ong findById(@PathVariable int id) {
        return ongService.findById(id);
    }

    @PostMapping
    public Ong save(@RequestBody Ong ong) {
        return ongService.save(ong);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        ongService.delete(id);
    }
}
