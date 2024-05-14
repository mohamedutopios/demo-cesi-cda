package com.example.bruteforce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;



@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final int LOCK_TIME_DURATION = 1;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails user = userDetailsService.loadUserByUsername(username);

        // Vérifier si le compte est verrouillé
        if (!user.isAccountNonLocked()) {
            LocalDateTime lockTime = loginAttemptService.getLockTime(username);
            if (lockTime != null && lockTime.plusMinutes(LOCK_TIME_DURATION).isBefore(LocalDateTime.now())) {
                // Déverrouiller automatiquement le compte après la période de verrouillage
                loginAttemptService.unlockAccount(username);
            } else {
                throw new LockedException("Account is locked");
            }
        }

        if (passwordEncoder.matches(password, user.getPassword())) {
            loginAttemptService.loginSucceeded(username);
            return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
        } else {
            loginAttemptService.loginFailed(username);
            throw new BadCredentialsException("Invalid username or password");
        }
    }
    
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
