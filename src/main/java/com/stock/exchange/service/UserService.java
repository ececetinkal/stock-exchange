package com.stock.exchange.service;

import com.stock.exchange.domain.user.User;
import com.stock.exchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

    public User getUser(@PathVariable String username) {
        Optional<User> user = userRepository.findByUsername(username);

        if (!user.isPresent())
            return null;

        return user.get();
    }

    public User createUser(@RequestBody User user) {
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());

        if(userOptional.isPresent()){
            return null;
        }

        user.setId(0L);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User deleteUser(@PathVariable String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (!userOptional.isPresent())
            return null;

        userRepository.deleteById(userOptional.get().getId());

        return userOptional.get();
    }

    public User updateUser(@RequestBody User user, @PathVariable String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (!userOptional.isPresent())
            return null;

        user.setId(userOptional.get().getId());
        userRepository.save(user);

        return user;
    }
}
