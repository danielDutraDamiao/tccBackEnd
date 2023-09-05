package com.backend.backendtcc.controller;


import com.backend.backendtcc.dto.request.LoginRequest;
import com.backend.backendtcc.dto.response.JwtResponseDTO;
import com.backend.backendtcc.model.User;
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

@RestController
public class Autenticate {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping(value = "/api/login")
    public ResponseEntity<JwtResponseDTO> login(@Valid @RequestBody LoginRequest loginRequst,
                                                BindingResult bindingResult) {

        // se o usuário existir
        User  user = userRepository.findByUsername(loginRequst.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        boolean password_equal = passwordEncoder.matches(loginRequst.getPassword(), user.getPassword());
        if (password_equal) {

            String token = jwtUtil.generateToken(user.getUsername());

            return ResponseEntity.status(HttpStatus.OK).body(JwtResponseDTO.fromEntity("Bearer", token, null));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }

}