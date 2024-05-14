package com.example.bruteforce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="LOGIN_ATTEMPT")
public class LoginAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private LocalDateTime timestamp;


    public LoginAttempt(String username, LocalDateTime now) {
        this.username = username;
        this.timestamp = now;
    }
}
