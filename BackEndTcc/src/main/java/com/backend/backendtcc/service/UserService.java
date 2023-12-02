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

    public boolean adicionarEcoPoints(Long idUsuario, BigDecimal ecoPoints) {
        Optional<User> userOptional = userRepository.findById(idUsuario);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            BigDecimal currentEcoPoints = user.getEcopoints();
            if (currentEcoPoints == null) {
                currentEcoPoints = BigDecimal.ZERO;
            }
            user.setEcopoints(currentEcoPoints.add(ecoPoints));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public BigDecimal obterEcoPointsDoUsuario(Long idUsuario) {
        Optional<User> userOptional = userRepository.findById(idUsuario);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            BigDecimal currentEcoPoints = user.getEcopoints();
            return currentEcoPoints != null ? currentEcoPoints : BigDecimal.ZERO;
        } else {
            return BigDecimal.ZERO; // Ou lançar uma exceção se o usuário não for encontrado.
        }
    }

    public boolean atualizarEcoPointsDoUsuario(Long idUsuario, BigDecimal ecoPoints) {
        Optional<User> userOptional = userRepository.findById(idUsuario);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            BigDecimal currentEcoPoints = user.getEcopoints();
            if (currentEcoPoints == null) {
                currentEcoPoints = BigDecimal.ZERO;
            }

            // Verifica se a subtração de EcoPoints não resultará em um valor negativo
            BigDecimal newEcoPoints = currentEcoPoints.add(ecoPoints);
            if (newEcoPoints.compareTo(BigDecimal.ZERO) >= 0) {
                user.setEcopoints(newEcoPoints);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }








}
