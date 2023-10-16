package com.backend.backendtcc.dto;

import com.backend.backendtcc.model.Cidade;
import lombok.Data;

import java.util.List;

@Data
public class OngDTO {
    private int idOng;
    private String nome;
    private String endereco;
    private String cnpj;
    private List<Cidade> cidades;
}
