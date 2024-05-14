package com.example.bruteforce.service;



import com.example.bruteforce.model.User;
import com.example.bruteforce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAccountNonLocked(true);
        userRepository.save(user);
    }

    public boolean unlockUser(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            user.setAccountNonLocked(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
