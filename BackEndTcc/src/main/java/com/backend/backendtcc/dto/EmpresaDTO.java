package com.backend.backendtcc.dto;

import lombok.Data;

@Data
public class EmpresaDTO {
    private int idEmpresa;
    private String nome;
    private String endereco;
    private String cnpj;
}
