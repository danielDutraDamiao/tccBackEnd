package com.backend.backendtcc.dto;

import lombok.Data;

@Data
public class SubCategoriaDTO {
    private int idSubCategoria;
    private String nomeSubCategoria;
    private CategoriaDTO categoria;

    // Métodos getter e setter
    public int getIdSubCategoria() {
        return idSubCategoria;
    }

    public void setIdSubCategoria(int idSubCategoria) {
        this.idSubCategoria = idSubCategoria;
    }

    public String getNomeSubCategoria() {
        return nomeSubCategoria;
    }

    public void setNomeSubCategoria(String nomeSubCategoria) {
        this.nomeSubCategoria = nomeSubCategoria;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }
}
