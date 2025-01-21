package com.customersphere.services;

import com.customersphere.entity.User;
import com.customersphere.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Service
public class AuthService extends UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    // Register a new user (Admin or Customer)
    public User register(User user) {
        // Check if the email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email is already in use");
        }

        // Encrypt the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Default role is CUSTOMER unless specified
        if (user.getRole() == null) {
            user.setRole(User.Role.CUSTOMER);
        }

        return userRepository.save(user);
    }

//     Login functionality: Authenticate user by email and password
public User login(String email, String password) {
    try {
        // Create authentication token with email and password
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);

        // Authenticate the user using AuthenticationManager
        authentication = authenticationManager.authenticate(authentication);

        // If authentication is successful, fetch the user details from the repository
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    } catch (BadCredentialsException e) {
        throw new BadCredentialsException("Invalid username or password");
    }
}


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getAuthorities());
    }
}
