package com.backend.backendtcc.dto.request;

import com.backend.backendtcc.model.Perfil;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class UserUpdateRequest {
    // Os mesmos campos do UserRequest, mas sem a validação @NotBlank no password
    private String name;
    private String username;
    private String email;
    private String password; // Sem a validação @NotBlank
    private Perfil perfil;
    private BigDecimal ecocoins;
}
