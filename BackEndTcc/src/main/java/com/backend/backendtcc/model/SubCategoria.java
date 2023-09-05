package com.backend.backendtcc.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "subcategoria")
public class SubCategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subcategoria")
    private int idSubCategoria;

    @Column(name = "nome_subcategoria")
    private String nomeSubCategoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @OneToMany(mappedBy = "subCategoria")
    private List<Produto> produtos;

    // Construtor com argumentos
    public SubCategoria(String nomeSubCategoria) {
        this.nomeSubCategoria = nomeSubCategoria;
    }
}
