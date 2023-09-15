package com.backend.backendtcc.controller;

import com.backend.backendtcc.dto.request.UserRequest;
import com.backend.backendtcc.dto.response.UserResponseDTO;
import com.backend.backendtcc.model.User;
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
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserRequest user){


//        if(bindingResult.hasErrors()){
//
//            List<FieldError> errors =  bindingResult.getFieldErrors().stream().map(e->new FieldError(e.getField(), e.getDefaultMessage())).collect(Collectors.toList());
//
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(UserResponseDto.fromEntity(new User(), errors, null));
//        }
//
//

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setName(user.getName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setUsername(user.getUsername());

        UserResponseDTO response = UserResponseDTO.fromEntity(this.userRepository.save(newUser));
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