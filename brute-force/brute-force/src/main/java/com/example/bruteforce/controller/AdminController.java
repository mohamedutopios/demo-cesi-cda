package com.example.bruteforce.controller;
import com.example.bruteforce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @PostMapping("/unlock/{username}")
    public ResponseEntity<String> unlockUser(@PathVariable String username) {
        boolean unlocked = userService.unlockUser(username);
        if (unlocked) {
            return ResponseEntity.ok("User account unlocked successfully");
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }
}
