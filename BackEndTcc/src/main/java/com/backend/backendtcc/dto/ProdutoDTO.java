package com.backend.backendtcc.dto;

import lombok.Data;
import org.w3c.dom.Text;

@Data
public class ProdutoDTO {
    private int idProduto;
    private String nomeProduto;
    private double precoProduto;
    private SubCategoriaDTO subcategoria;
    private String imagemProduto;

    // Métodos getter e setter

    public void setCategoriaProduto(CategoriaDTO categoriaProduto) {
        this.subcategoria = subcategoria;
    }
}
