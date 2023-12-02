package com.backend.backendtcc.controller;

import com.backend.backendtcc.dto.request.RecuperarSenhaRequest;
import com.backend.backendtcc.dto.request.UserRequest;
import com.backend.backendtcc.dto.request.UserUpdateRequest;
import com.backend.backendtcc.dto.response.UserResponseDTO;
import com.backend.backendtcc.model.Perfil;
import com.backend.backendtcc.model.User;
import com.backend.backendtcc.repository.PerfilRepository;
import com.backend.backendtcc.repository.UserRepository;
import com.backend.backendtcc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
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

    @Autowired
    private UserService userService;


    @GetMapping("listar")
    public ResponseEntity<List<UserResponseDTO>> listAll(){

        List<User> list = this.userRepository.findAll();

        List<UserResponseDTO> userList = list.stream().map(e -> UserResponseDTO.fromEntity(e)).collect(Collectors.toList());

        return ResponseEntity.ok().body(userList);

    }

    @GetMapping("/{id}/ecopoints")
    public ResponseEntity<BigDecimal> getEcoPoints(@PathVariable Long id) {
        BigDecimal ecoPoints = userService.obterEcoPointsDoUsuario(id);
        return ResponseEntity.ok(ecoPoints);
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
    public ResponseEntity<UserResponseDTO> edit(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        Optional<User> opUser = userRepository.findById(id);

        if (opUser.isPresent()) {
            User user = opUser.get();

            // Atualize os campos conforme necessário
            user.setName(userUpdateRequest.getName());
            user.setUsername(userUpdateRequest.getUsername());
            user.setEmail(userUpdateRequest.getEmail());

            // Atualize a senha somente se uma nova senha for fornecida
            if (userUpdateRequest.getPassword() != null && !userUpdateRequest.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(userUpdateRequest.getPassword()));
            }

            // Atualizar ecocoins e perfil, se necessário
            user.setEcocoins(userUpdateRequest.getEcocoins());
            user.setEcopoints(userUpdateRequest.getEcopoints());


            if (userUpdateRequest.getPerfil() != null) {
                // Aqui, você pode adicionar lógica para atualizar o perfil do usuário, se necessário
            }

            User updatedUser = userRepository.save(user);
            return ResponseEntity.ok(UserResponseDTO.fromEntity(updatedUser));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/recuperarSenha")
    public ResponseEntity<String> recuperarSenha(@RequestBody RecuperarSenhaRequest recuperarSenhaRequest) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername(recuperarSenhaRequest.getUsername()));

        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

        User user = userOptional.get();

        // Verifica se a nova senha e a confirmação são iguais
        if (!recuperarSenhaRequest.getNovaSenha().equals(recuperarSenhaRequest.getConfirmarNovaSenha())) {
            return ResponseEntity.badRequest().body("A confirmação da nova senha não corresponde.");
        }

        // Atualiza a senha do usuário
        user.setPassword(passwordEncoder.encode(recuperarSenhaRequest.getNovaSenha()));
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }



    @PutMapping("/updateEcoPoints/{userId}")
    public ResponseEntity<?> updateEcoPoints(@PathVariable Long userId, @RequestParam BigDecimal ecoPoints) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        User user = userOptional.get();
        BigDecimal currentEcoPoints = user.getEcopoints();
        if (currentEcoPoints == null) {
            currentEcoPoints = BigDecimal.ZERO;
        }
        user.setEcopoints(currentEcoPoints.add(ecoPoints));
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }



    @PutMapping("/updateEcoCoins/{userId}")
    public ResponseEntity<?> updateEcoCoins(@PathVariable Long userId, @RequestParam BigDecimal ecoCoins) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        User user = userOptional.get();
        // Inicializa ecocoins com zero se for nulo
        if (user.getEcocoins() == null) {
            user.setEcocoins(BigDecimal.ZERO);
        }
        user.setEcocoins(user.getEcocoins().add(ecoCoins));
        userRepository.save(user);

        return ResponseEntity.ok().build();
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