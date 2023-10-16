package com.backend.backendtcc.dto;

import com.backend.backendtcc.model.Cidade;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CidadeDTO {
    private int idCidade;
    private String nomeCidade;
    private String nomeEstado;  // Adicione esta linha

    public CidadeDTO toDTO(Cidade cidade) {
        CidadeDTO dto = new CidadeDTO();
        dto.setIdCidade(cidade.getIdCidade());
        dto.setNomeCidade(cidade.getNomeCidade());
        dto.setNomeEstado(cidade.getEstado().getNomeEstado());
        return dto;
    }
}
