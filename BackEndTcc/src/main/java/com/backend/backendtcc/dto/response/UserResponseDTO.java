package com.backend.backendtcc.dto.response;


import com.backend.backendtcc.model.User;
import lombok.Data;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserResponseDTO {

    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<FieldError> erros;

    public static UserResponseDTO fromEntity(User user){
        var response = new UserResponseDTO();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setUsername(user.getUsername());
        response.setPassword(user.getPassword());
        response.setEmail(user.getEmail());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());

        return response;
    }

}
