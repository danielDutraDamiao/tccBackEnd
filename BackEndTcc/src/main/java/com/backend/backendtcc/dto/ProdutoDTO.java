package com.backend.backendtcc.dto;

import lombok.Data;
import org.w3c.dom.Text;

@Data
public class ProdutoDTO {
    private int idProduto;
    private String nomeProduto;
    private double precoProduto;
    private CategoriaDTO categoriaProduto;
    private String imagemProduto;

    // Métodos getter e setter
    public CategoriaDTO getCategoriaProduto() {
        return categoriaProduto;
    }

    public void setCategoriaProduto(CategoriaDTO categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }
}
