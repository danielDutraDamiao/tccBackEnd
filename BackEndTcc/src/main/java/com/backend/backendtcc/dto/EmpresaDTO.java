package com.backend.backendtcc.dto;

import com.backend.backendtcc.model.Cidade;
import lombok.Data;

import java.util.List;

@Data
public class EmpresaDTO {
    private int idEmpresa;
    private String nome;
    private String endereco;
    private String cnpj;
    private Cidade cidade;
}
