package com.backend.backendtcc.model;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private int idProduto;

    @Column(name = "nome_produto")
    private String nomeProduto;

    @Column(name = "preco_produto")
    private double precoProduto;

    @Column(name = "quantidade_produto")
    private Double quantidade;

    @Column(name = "status_inventario")
    private String statusInventario;

    @Column(name = "avaliacao")
    private Double avaliacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_subcategoria")
    private SubCategoria subCategoria;

    @Lob
    @Column(name = "imagem_produto", columnDefinition = "LONGTEXT")
    private String imagemProduto;

    // Construtor com argumentos
    public Produto(String nomeProduto, double precoProduto, SubCategoria subcategoria, String imagemProduto) {
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
        this.subCategoria = subcategoria;
        this.imagemProduto = imagemProduto;
    }

    public SubCategoria getSubCategoria() {
        return subCategoria;
    }
}

