package com.backend.backendtcc.service;

import com.backend.backendtcc.model.Cidade;
import com.backend.backendtcc.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> findAll() {
        return cidadeRepository.findAll();
    }

    public Cidade findById(int id) {
        return cidadeRepository.findById(id).orElse(null);
    }

    public Cidade save(Cidade cidade) {
        return cidadeRepository.save(cidade);
    }

    public void delete(int id) {
        cidadeRepository.deleteById(id);
    }
}
