package com.example.bruteforce.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String login(HttpServletRequest request, Model model) {
//        HttpSession session = request.getSession(false);
//        String errorMessage = null;
//        if (session != null) {
//            AuthenticationException ex = (AuthenticationException) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
//            if (ex instanceof LockedException) {
//                errorMessage = "Your account has been locked due to multiple failed login attempts.";
//            } else if (ex != null) {
//                errorMessage = "Invalid username or password.";
//            }
//        }
//        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }
}

