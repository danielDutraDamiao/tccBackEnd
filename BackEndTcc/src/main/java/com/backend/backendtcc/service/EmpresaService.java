package com.backend.backendtcc.service;

import com.backend.backendtcc.dto.EmpresaDTO;
import com.backend.backendtcc.model.Empresa;
import com.backend.backendtcc.repository.EmpresaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public List<EmpresaDTO> listarEmpresas() {
        List<Empresa> empresas = empresaRepository.findAll();
        return empresas.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EmpresaDTO obterEmpresa(int idEmpresa) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(idEmpresa);
        return empresaOptional.map(this::convertToDTO).orElse(null);
    }

    public EmpresaDTO criarEmpresa(EmpresaDTO empresaDTO) {
        Empresa empresa = convertToEntity(empresaDTO);
        Empresa empresaSalva = empresaRepository.save(empresa);
        return convertToDTO(empresaSalva);
    }

    public EmpresaDTO atualizarEmpresa(int idEmpresa, EmpresaDTO empresaDTO) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(idEmpresa);
        if (empresaOptional.isPresent()) {
            Empresa empresa = empresaOptional.get();
            BeanUtils.copyProperties(empresaDTO, empresa);
            Empresa empresaAtualizada = empresaRepository.save(empresa);
            return convertToDTO(empresaAtualizada);
        }
        return null;
    }

    public void deletarEmpresa(int idEmpresa) {
        empresaRepository.deleteById(idEmpresa);
    }

    private EmpresaDTO convertToDTO(Empresa empresa) {
        EmpresaDTO empresaDTO = new EmpresaDTO();
        BeanUtils.copyProperties(empresa, empresaDTO);
        return empresaDTO;
    }

    private Empresa convertToEntity(EmpresaDTO empresaDTO) {
        Empresa empresa = new Empresa();
        BeanUtils.copyProperties(empresaDTO, empresa);
        return empresa;
    }
}
