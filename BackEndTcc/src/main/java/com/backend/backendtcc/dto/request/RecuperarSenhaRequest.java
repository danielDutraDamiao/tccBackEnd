package com.backend.backendtcc.dto.request;

import lombok.Data;

@Data
public class RecuperarSenhaRequest {
    private String username;
    private String novaSenha;
    private String confirmarNovaSenha;
    // Getters e setters
}