package com.backend.backendtcc.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class EstadoDTO {
    private int idEstado;

    private String nomeEstado;

    @JsonManagedReference
    private List<CidadeDTO> cidades;

    public EstadoDTO(int idEstado, String nomeEstado) {
        this.idEstado = idEstado;
        this.nomeEstado = nomeEstado;
    }
}
