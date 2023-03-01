package com.br.user.controller;

import com.br.user.entity.Motorcycle;
import com.br.user.entity.User;
import com.br.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User registeredUser = userService.register(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> findByEmail(@PathVariable String email) {
        User user = userService.findByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/{userId}/motorcycles/{motorcycleId}")
    public ResponseEntity<User> updateUserMotorcycle(@PathVariable String userId, @PathVariable Long motorcycleId) {
        User updatedUser = userService.updateUserMotorcycle(userId, motorcycleId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }


}

