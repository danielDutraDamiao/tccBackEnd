package com.backend.backendtcc.dto.response;


import lombok.Data;
import org.springframework.validation.FieldError;

import java.util.List;

@Data
public class JwtResponseDTO {

    private String type;
    private String token;

    private List<FieldError> erros;

    public static JwtResponseDTO fromEntity(String type, String token, List<FieldError> erros){
        var response = new JwtResponseDTO();

        response.setType(type);
        response.setToken(token);
        response.setErros(erros);

        return response;
    }

}
