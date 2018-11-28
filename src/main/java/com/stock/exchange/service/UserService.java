package com.stock.exchange.service;

import com.stock.exchange.domain.user.User;
import com.stock.exchange.exception.UserNotFoundException;
import com.stock.exchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User getUser(@PathVariable String username) throws UserNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (!user.isPresent())
            throw new UserNotFoundException("Username: " + username);

        return user.get();
    }

    public User createUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User deleteUser(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent())
            return null;

        userRepository.deleteById(id);

        return userOptional.get();
    }

    public User updateUser(@RequestBody User user, @PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent())
            return null;

        user.setId(id);
        userRepository.save(user);

        return user;
    }
}
