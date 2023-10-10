package com.backend.backendtcc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CidadeDTO {
    private int idCidade;
    private String nomeCidade;
    private EstadoDTO estado;

    public CidadeDTO(int idCidade, String nomeCidade, EstadoDTO estado) {
        this.idCidade = idCidade;
        this.nomeCidade = nomeCidade;
        this.estado = estado;
    }
}
