package com.backend.backendtcc.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private int idEmpresa;

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
    public Empresa(String nome, String endereco, String cnpj, Cidade cidade) {
        this.nome = nome;
        this.endereco = endereco;
        this.cnpj = cnpj;
        this.cidade = cidade;
    }
}
