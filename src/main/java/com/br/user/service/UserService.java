package com.br.user.service;



import com.br.user.entity.User;
import com.br.user.exception.UserAlreadyExistsException;
import com.br.user.exception.UserNotFoundException;
import com.br.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user) {
        // Verifica se o usuário já existe
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists with email: " + user.getEmail());
        }

        // Criptografa a senha
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Salva o usuário no banco de dados
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

}

