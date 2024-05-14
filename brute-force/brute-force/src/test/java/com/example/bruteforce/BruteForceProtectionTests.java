package com.example.bruteforce;

import com.example.bruteforce.model.User;
import com.example.bruteforce.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BruteForceProtectionTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@BeforeEach
	public void setup() {
		userRepository.deleteAll();
		User user = new User();
		user.setUsername("testuser");
		user.setPassword(passwordEncoder.encode("password"));
		user.setAccountNonLocked(true);
		userRepository.save(user);
	}

	@Test
	public void testBruteForceProtection() throws Exception {
		String username = "testuser";
		String password = "wrongpassword";

		// Simulate failed login attempts
		for (int i = 0; i < 3; i++) {
			mockMvc.perform(formLogin().user(username).password(password))
					.andExpect(unauthenticated())
					.andExpect(redirectedUrl("/login?error"));
		}

		// Attempt to login with correct credentials after account is locked
		mockMvc.perform(formLogin().user(username).password("password"))
				.andExpect(unauthenticated())
				.andExpect(redirectedUrl("/login?error"));

		// Unlock the account and test successful login
		User user = userRepository.findByUsername(username).orElseThrow();
		user.setAccountNonLocked(true);
		userRepository.save(user);

		mockMvc.perform(formLogin().user(username).password("password"))
				.andExpect(authenticated());
	}
}
