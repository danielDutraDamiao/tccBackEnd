package com.backend.backendtcc.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import org.w3c.dom.Text;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @Lob
    @Column(name = "imagem_produto", columnDefinition="LONGTEXT")
    private String imagemProduto;

    // Construtor com argumentos
    public Produto(String nomeProduto, double precoProduto, Categoria categoria, String imagemProduto) {
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
        this.categoria = categoria;
        this.imagemProduto = imagemProduto;
    }

    public Categoria getCategoria() {
        return categoria;
    }
}
