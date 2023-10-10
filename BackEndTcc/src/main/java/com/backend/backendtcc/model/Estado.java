package com.backend.backendtcc.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "estado")
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private int idEstado;

    @Column(name = "nome_estado")
    private String nomeEstado;

    @JsonManagedReference
    @OneToMany(mappedBy = "estado")
    private List<Cidade> cidades;

    public Estado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }
}
