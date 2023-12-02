package com.backend.backendtcc.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique=true)
    private String username;
    private String password;

    @ManyToOne
    @JoinColumn(name = "perfil_id", referencedColumnName = "id_perfil")
    private Perfil perfil;

    @Column(unique=true)
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Column
    private BigDecimal ecocoins;

    @Column
    private BigDecimal ecopoints;


    @PrePersist
    public void prePersist(){
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate(){
        this.setUpdatedAt(LocalDateTime.now());
    }


}
