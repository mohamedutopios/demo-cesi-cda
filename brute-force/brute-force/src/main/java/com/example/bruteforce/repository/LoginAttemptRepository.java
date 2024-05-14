package com.example.bruteforce.repository;

import com.example.bruteforce.model.LoginAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

// Interface de dépôt pour gérer les tentatives de connexion
public interface LoginAttemptRepository extends JpaRepository<LoginAttempt, Long> {

    // Trouve toutes les tentatives de connexion pour un utilisateur après une certaine heure
    List<LoginAttempt> findByUsernameAndTimestampAfter(String username, LocalDateTime timestamp);

    // Trouve la dernière tentative de connexion pour un utilisateur
    LoginAttempt findTopByUsernameOrderByTimestampDesc(String username);
}
