package com.backend.backendtcc.dto;

import lombok.Data;

@Data
public class SubCategoriaDTO {
    private int idSubCategoria;
    private String nomeSubCategoria;
    private CategoriaDTO categoria;

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }
}
