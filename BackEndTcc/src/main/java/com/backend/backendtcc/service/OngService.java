package com.backend.backendtcc.service;

import com.backend.backendtcc.model.Ong;
import com.backend.backendtcc.repository.CidadeRepository;
import com.backend.backendtcc.repository.OngRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OngService {

    @Autowired
    private OngRepository ongRepository;

    public List<Ong> findAll() {
        return ongRepository.findAll();
    }

    public Ong findById(int id) {
        return ongRepository.findById(id).orElse(null);
    }

    public Ong save(Ong ong) {
        return ongRepository.save(ong);
    }

    public void delete(int id) {
        ongRepository.deleteById(id);
    }
}
