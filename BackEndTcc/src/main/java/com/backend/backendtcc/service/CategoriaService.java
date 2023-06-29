package com.backend.backendtcc.service;

import com.backend.backendtcc.dto.CategoriaDTO;
import com.backend.backendtcc.model.Categoria;
import com.backend.backendtcc.repository.CategoriaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaDTO> listarCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CategoriaDTO obterCategoria(int idCategoria) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(idCategoria);
        return categoriaOptional.map(this::convertToDTO).orElse(null);
    }

    public CategoriaDTO criarCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = convertToEntity(categoriaDTO);
        Categoria categoriaSalva = categoriaRepository.save(categoria);
        return convertToDTO(categoriaSalva);
    }

    public CategoriaDTO atualizarCategoria(int idCategoria, CategoriaDTO categoriaDTO) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(idCategoria);
        if (categoriaOptional.isPresent()) {
            Categoria categoria = categoriaOptional.get();
            BeanUtils.copyProperties(categoriaDTO, categoria);
            Categoria categoriaAtualizada = categoriaRepository.save(categoria);
            return convertToDTO(categoriaAtualizada);
        }
        return null;
    }

    public void deletarCategoria(int idCategoria) {
        categoriaRepository.deleteById(idCategoria);
    }

    private CategoriaDTO convertToDTO(Categoria categoria) {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        BeanUtils.copyProperties(categoria, categoriaDTO);
        return categoriaDTO;
    }

    private Categoria convertToEntity(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        BeanUtils.copyProperties(categoriaDTO, categoria);
        return categoria;
    }
}
