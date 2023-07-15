package com.backend.backendtcc.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private int idCategoria;

    @Column(name = "nome_categoria")
    private String nomeCategoria;

    @OneToMany(mappedBy = "categoria")
    private List<SubCategoria> subCategorias;

    // Construtor com argumentos
    public Categoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }
}
