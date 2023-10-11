package com.backend.backendtcc.dto;

import lombok.Data;
import org.w3c.dom.Text;

@Data
public class ProdutoDTO {
    private int idProduto;
    private String nomeProduto;
    private Double precoProduto;
    private SubCategoriaDTO subcategoria;
    private String imagemProduto;
    private Double quantidade;
    private String statusInventario;
    private Double avaliacao;

    // Métodos getter e setter

    public void setCategoriaProduto(CategoriaDTO categoriaProduto) {
        this.subcategoria = subcategoria;
    }
}
