package com.backend.backendtcc.dto;

import lombok.Data;

@Data
public class ClienteDTO {
    private int idCliente;
    private String nome;
    private String email;
    private String cpf;
}
