package com.example.bruteforce.security;

import com.example.bruteforce.model.LoginAttempt;
import com.example.bruteforce.model.User;
import com.example.bruteforce.repository.LoginAttemptRepository;
import com.example.bruteforce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoginAttemptService {

    private static final int MAX_ATTEMPTS = 3;
    private static final int LOCK_TIME_DURATION = 1;

    @Autowired
    private LoginAttemptRepository loginAttemptRepository;

    @Autowired
    private UserRepository userRepository;

    public void loginSucceeded(String username) {
        // Supprime toutes les tentatives de connexion échouées pour l'utilisateur après une connexion réussie
        loginAttemptRepository.deleteAll(loginAttemptRepository.findByUsernameAndTimestampAfter(username, LocalDateTime.now().minusMinutes(LOCK_TIME_DURATION)));
    }

    public void loginFailed(String username) {
        LocalDateTime now = LocalDateTime.now();
        // Enregistre une nouvelle tentative de connexion échouée pour l'utilisateur
        loginAttemptRepository.save(new LoginAttempt(username, now));

        if (isBruteForceAttack(username)) {
            User user = userRepository.findByUsername(username).orElseThrow();
            user.setAccountNonLocked(false);
            userRepository.save(user);
        }
    }

    private boolean isBruteForceAttack(String username) {
        LocalDateTime lockTime = LocalDateTime.now().minusMinutes(LOCK_TIME_DURATION);
        // Vérifie si l'utilisateur a dépassé le nombre maximum de tentatives de connexion
        return loginAttemptRepository.findByUsernameAndTimestampAfter(username, lockTime).size() >= MAX_ATTEMPTS;
    }

    public LocalDateTime getLockTime(String username) {
        // Trouve la dernière tentative de connexion pour l'utilisateur
        LoginAttempt attempt = loginAttemptRepository.findTopByUsernameOrderByTimestampDesc(username);
        // Retourne le timestamp de cette tentative ou null si aucune tentative n'existe
        return attempt != null ? attempt.getTimestamp() : null;
    }

    public void unlockAccount(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        user.setAccountNonLocked(true);
        userRepository.save(user);
    }
}
