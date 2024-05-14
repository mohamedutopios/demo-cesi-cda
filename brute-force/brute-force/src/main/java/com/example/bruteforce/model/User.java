package com.example.bruteforce.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="person")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private boolean accountNonLocked = true;


}