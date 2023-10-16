package com.backend.backendtcc.service;

import com.backend.backendtcc.dto.CidadeDTO;
import com.backend.backendtcc.model.Cidade;
import com.backend.backendtcc.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public CidadeDTO toDTO(Cidade cidade) {
        CidadeDTO dto = new CidadeDTO();
        dto.setIdCidade(cidade.getIdCidade());
        dto.setNomeCidade(cidade.getNomeCidade());
        if(cidade.getEstado() != null) { // Verifica se há um estado associado
            dto.setNomeEstado(cidade.getEstado().getNomeEstado());
        }
        return dto;
    }
    public List<CidadeDTO> findAll() {
        return cidadeRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CidadeDTO findById(int id) {
        Cidade cidade = cidadeRepository.findById(id).orElse(null);
        return cidade != null ? toDTO(cidade) : null;
    }


    public Cidade save(Cidade cidade) {
        return cidadeRepository.save(cidade);
    }

    public void delete(int id) {
        cidadeRepository.deleteById(id);
    }
}
