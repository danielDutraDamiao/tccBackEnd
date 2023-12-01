package com.backend.backendtcc.service;


import com.backend.backendtcc.model.User;

import com.backend.backendtcc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public boolean atualizarEcoCoinsUsuario(Long idUsuario, BigDecimal ecoCoins) {
        Optional<User> userOptional = userRepository.findById(idUsuario);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Garantir que o ecoCoins seja positivo para a subtração
            BigDecimal valorSubtrair = ecoCoins.abs();
            if (user.getEcocoins().compareTo(valorSubtrair) >= 0) {
                user.setEcocoins(user.getEcocoins().subtract(valorSubtrair));
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }



}
