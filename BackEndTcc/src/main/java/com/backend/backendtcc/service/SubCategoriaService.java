package com.backend.backendtcc.service;

import com.backend.backendtcc.dto.CategoriaDTO;
import com.backend.backendtcc.dto.SubCategoriaDTO;
import com.backend.backendtcc.model.Categoria;
import com.backend.backendtcc.model.SubCategoria;
import com.backend.backendtcc.repository.SubCategoriaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubCategoriaService {
    private final SubCategoriaRepository subCategoriaRepository;

    public SubCategoriaService(SubCategoriaRepository subCategoriaRepository) {
        this.subCategoriaRepository = subCategoriaRepository;
    }

    public List<SubCategoriaDTO> listarSubCategorias() {
        List<SubCategoria> subcategorias = subCategoriaRepository.findAll();
        return subcategorias.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SubCategoriaDTO obterSubCategoria(int idSubCategoria) {
        Optional<SubCategoria> subCategoriaOptional = subCategoriaRepository.findById(idSubCategoria);
        return subCategoriaOptional.map(this::convertToDTO).orElse(null);
    }

    public SubCategoriaDTO criarSubCategoria(SubCategoriaDTO subCategoriaDTO) {
        SubCategoria subCategoria = convertToEntity(subCategoriaDTO);
        SubCategoria subCategoriaSalva = subCategoriaRepository.save(subCategoria);
        return convertToDTO(subCategoriaSalva);
    }

    public SubCategoriaDTO atualizarSubCategoria(int idSubCategoria, SubCategoriaDTO subCategoriaDTO) {
        Optional<SubCategoria> subCategoriaOptional = subCategoriaRepository.findById(idSubCategoria);
        if (subCategoriaOptional.isPresent()) {
            SubCategoria subCategoria = subCategoriaOptional.get();
            BeanUtils.copyProperties(subCategoriaDTO, subCategoria);
            SubCategoria subCategoriaAtualizada = subCategoriaRepository.save(subCategoria);
            return convertToDTO(subCategoriaAtualizada);
        }
        return null;
    }

    public void deletarSubCategoria(int idSubCategoria) {
        subCategoriaRepository.deleteById(idSubCategoria);
    }

    private SubCategoriaDTO convertToDTO(SubCategoria subCategoria) {
        SubCategoriaDTO subCategoriaDTO = new SubCategoriaDTO();
        subCategoriaDTO.setIdSubCategoria(subCategoria.getIdSubCategoria());
        subCategoriaDTO.setNomeSubCategoria(subCategoria.getNomeSubCategoria());

        Categoria categoria = subCategoria.getCategoria();
        if (categoria != null) {
            CategoriaDTO categoriaDTO = new CategoriaDTO();
            categoriaDTO.setIdCategoria(categoria.getIdCategoria());
            categoriaDTO.setNomeCategoria(categoria.getNomeCategoria());
            subCategoriaDTO.setCategoria(categoriaDTO);
        }

        return subCategoriaDTO;
    }

    private SubCategoria convertToEntity(SubCategoriaDTO subCategoriaDTO) {
        SubCategoria subCategoria = new SubCategoria();
        BeanUtils.copyProperties(subCategoriaDTO, subCategoria);
        return subCategoria;
    }

    public List<SubCategoriaDTO> listarSubCategoriasPorCategoria(int idCategoria) {
        List<SubCategoria> subCategorias = subCategoriaRepository.findAllByCategoria_IdCategoria(idCategoria);
        return subCategorias.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

}
