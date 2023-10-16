package com.backend.backendtcc.model;

import com.backend.backendtcc.dto.CidadeDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ong")
public class Ong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ong")
    private int idOng;

    @Column(name = "nome")
    private String nome;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "cnpj")
    private String cnpj;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "id_cidade")
    private Cidade cidade;

    // Construtor com argumentos
    public Ong(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.cnpj = cnpj;
    }
}
