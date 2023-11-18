package com.backend.backendtcc.service;

import com.backend.backendtcc.dto.PerfilDTO;
import com.backend.backendtcc.model.Perfil;
import com.backend.backendtcc.repository.PerfilRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PerfilService {
    private final PerfilRepository perfilRepository;

    public PerfilService(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    public List<PerfilDTO> listarPerfils() {
        List<Perfil> perfils = perfilRepository.findAll();
        return perfils.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PerfilDTO obterPerfil(int idPerfil) {
        Optional<Perfil> perfilOptional = perfilRepository.findById(idPerfil);
        return perfilOptional.map(this::convertToDTO).orElse(null);
    }

    public PerfilDTO criarPerfil(PerfilDTO perfilDTO) {
        Perfil perfil = convertToEntity(perfilDTO);
        Perfil perfilSalva = perfilRepository.save(perfil);
        return convertToDTO(perfilSalva);
    }

    public PerfilDTO atualizarPerfil(int idPerfil, PerfilDTO perfilDTO) {
        Optional<Perfil> perfilOptional = perfilRepository.findById(idPerfil);
        if (perfilOptional.isPresent()) {
            Perfil perfil = perfilOptional.get();
            BeanUtils.copyProperties(perfilDTO, perfil);
            Perfil perfilAtualizada = perfilRepository.save(perfil);
            return convertToDTO(perfilAtualizada);
        }
        return null;
    }

    public void deletarPerfil(int idPerfil) {
        perfilRepository.deleteById(idPerfil);
    }

    private PerfilDTO convertToDTO(Perfil perfil) {
        PerfilDTO perfilDTO = new PerfilDTO();
        BeanUtils.copyProperties(perfil, perfilDTO);
        return perfilDTO;
    }

    private Perfil convertToEntity(PerfilDTO perfilDTO) {
        Perfil perfil = new Perfil();
        BeanUtils.copyProperties(perfilDTO, perfil);
        return perfil;
    }
}
