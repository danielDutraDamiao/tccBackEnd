package com.backend.backendtcc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class EstadoDTO {
    private int idEstado;
    private String nomeEstado;
    private List<CidadeDTO> cidades;

    public EstadoDTO(int idEstado, String nomeEstado) {
        this.idEstado = idEstado;
        this.nomeEstado = nomeEstado;
    }
}
