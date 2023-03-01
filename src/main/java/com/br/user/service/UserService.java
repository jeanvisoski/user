package com.br.user.service;



import com.br.user.entity.Motorcycle;
import com.br.user.entity.User;
import com.br.user.exception.ResourceNotFoundException;
import com.br.user.exception.UserAlreadyExistsException;
import com.br.user.exception.UserNotFoundException;
import com.br.user.repository.MotorcycleRepository;
import com.br.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MotorcycleRepository motorcycleRepository;

    public User register(User user) {
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User updateUserMotorcycle(String userId, Long motorcycleId) {
        User user = userRepository.findById(userId).get();
        Motorcycle motorcycle = motorcycleRepository.findById(motorcycleId).get();

        // Encontra a moto correspondente na lista de motos do usuário
        User userMotorcycle = user;

        // Atualiza os atributos da moto correspondente
        userMotorcycle.setMotorcycles(motorcycle);

        // Salva as atualizações no banco de dados
        userRepository.save(userMotorcycle);

        return user;
    }


}

