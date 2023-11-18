package com.backend.backendtcc.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "perfil")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil")
    private int idPerfil;

    @Column(name = "nome")
    private String perfil;


    // Construtor com argumentos
    public Perfil(String nome) {
        this.perfil = nome;
    }
}
