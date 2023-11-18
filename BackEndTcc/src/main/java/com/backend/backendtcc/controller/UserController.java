package com.backend.backendtcc.controller;

import com.backend.backendtcc.dto.request.UserRequest;
import com.backend.backendtcc.dto.response.UserResponseDTO;
import com.backend.backendtcc.model.Perfil;
import com.backend.backendtcc.model.User;
import com.backend.backendtcc.repository.PerfilRepository;
import com.backend.backendtcc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PerfilRepository perfilRepository;


    @GetMapping("listar")
    public ResponseEntity<List<UserResponseDTO>> listAll(){

        List<User> list = this.userRepository.findAll();

        List<UserResponseDTO> userList = list.stream().map(e -> UserResponseDTO.fromEntity(e)).collect(Collectors.toList());

        return ResponseEntity.ok().body(userList);

    }


    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> listById(@PathVariable Long id){

        Optional<User> user = this.userRepository.findById(id);

        if(user.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(UserResponseDTO.fromEntity(user.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }



    @PostMapping("create")
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserRequest userRequest) {
        User newUser = new User();
        newUser.setName(userRequest.getName());
        newUser.setUsername(userRequest.getUsername());
        newUser.setEmail(userRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        // Buscar perfil pelo id
        Perfil perfil = perfilRepository.findById(userRequest.getPerfil().getIdPerfil())
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));
        newUser.setPerfil(perfil);

        // Salvar o usuário
        userRepository.save(newUser);

        // Criar e retornar a resposta
        UserResponseDTO response = UserResponseDTO.fromEntity(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }




    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> edit(@PathVariable Long id, @Valid @RequestBody UserRequest userRequest){

        Optional<User> opUser = this.userRepository.findById(id);

        if(opUser.isPresent()){
            User user = opUser.get();
            user.setEmail(userRequest.getEmail());
            user.setName(userRequest.getName());
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            user.setUsername(userRequest.getUsername());

            UserResponseDTO response = UserResponseDTO.fromEntity(this.userRepository.save(user));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        Optional<User> user = this.userRepository.findById(id);

        if(user.isPresent()){

            this.userRepository.deleteById(id);

            return ResponseEntity.status(HttpStatus.OK).build();

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}