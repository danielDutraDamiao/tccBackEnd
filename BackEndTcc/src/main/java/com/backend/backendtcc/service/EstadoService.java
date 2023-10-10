package com.backend.backendtcc.service;

import com.backend.backendtcc.model.Estado;
import com.backend.backendtcc.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> findAll() {
        return estadoRepository.findAll();
    }

    public Estado findById(int id) {
        return estadoRepository.findById(id).orElse(null);
    }

    public Estado save(Estado estado) {
        return estadoRepository.save(estado);
    }

    public void delete(int id) {
        estadoRepository.deleteById(id);
    }
}
