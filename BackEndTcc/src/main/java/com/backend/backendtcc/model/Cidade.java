package com.backend.backendtcc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cidade")
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cidade")
    private int idCidade;

    @Column(name = "nome_cidade")
    private String nomeCidade;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_estado")
    private Estado estado;

    @JsonBackReference
    @OneToMany(mappedBy = "cidade")
    private List<Ong> ongs;

    public Cidade(String nomeCidade, Estado estado) {
        this.nomeCidade = nomeCidade;
        this.estado = estado;
    }
}
