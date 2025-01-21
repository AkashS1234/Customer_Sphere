package com.customersphere.services;

import com.customersphere.entity.User;
import com.customersphere.entity.User.Role;
import com.customersphere.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Create a new user (for registration or by Admin)
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email is already in use.");
        }
        // Hash password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Set default role to CUSTOMER if not specified
        if (user.getRole() == null) {
            user.setRole(Role.CUSTOMER);
        }

        return userRepository.save(user);
    }

    // Update an existing user's information
    public User updateUser(User user) {
        if (!userRepository.existsById(user.getId())) {
            throw new IllegalArgumentException("User not found.");
        }
        // Hash the password if updated
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        return userRepository.save(user);
    }

    // Get a user by their ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Get all users (Admin only)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Delete a user by their ID
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found.");
        }
        userRepository.deleteById(id);
    }

    // Find users by role
    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }

    // This method is used by AuthService to load user by username (email)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getAuthorities());
    }
}
