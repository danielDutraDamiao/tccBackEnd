package com.backend.backendtcc.controller;


import com.backend.backendtcc.dto.request.LoginRequest;
import com.backend.backendtcc.dto.response.JwtResponseDTO;
import com.backend.backendtcc.model.User;
import com.backend.backendtcc.repository.PerfilRepository;
import com.backend.backendtcc.repository.UserRepository;
import com.backend.backendtcc.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Autenticate {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping(value = "/api/login")
    public ResponseEntity<JwtResponseDTO> login(@Valid @RequestBody LoginRequest loginRequest,
                                                BindingResult bindingResult) {
        // Verificar se o usuário existe
        User user = userRepository.findByUsername(loginRequest.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        boolean passwordEqual = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
        if (passwordEqual) {
            try {
                Map<String, Object> claims = new HashMap<>();
                claims.put("perfil", user.getPerfil().getPerfil()); // Adicionar o perfil
                claims.put("userId", user.getId()); // Adicionar o ID do usuário

                String token = jwtUtil.generateTokenWithClaims(user.getUsername(), claims);
                return ResponseEntity.status(HttpStatus.OK).body(JwtResponseDTO.fromEntity("Bearer", token, null));
            } catch (Exception e) {
                e.printStackTrace(); // Logar a exceção para diagnóstico
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


}