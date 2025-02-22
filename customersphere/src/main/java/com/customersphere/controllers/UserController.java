package com.customersphere.controllers;

import com.customersphere.entity.User;

import com.customersphere.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
//    @PreAuthorize("hasRole('ADMIN')")

    // Create user
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // Get user by ID
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }



    // Update user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        Optional<User> existingUser = userService.getUserById(id);
        if (existingUser.isPresent()) {
            User updatedUser = userService.updateUser(existingUser.get(), user);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

//@RestController
//@RequestMapping("/api/users")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    // Admin: Create a new user (Customer)
//    @PostMapping
//    public ResponseEntity<User> createUser(@RequestBody User user) {
//        try {
//            User createdUser = userService.createUser(user);
//            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    // Admin or Customer: Update User
//    @PutMapping("/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user, Authentication authentication) {
//        // Check if the authenticated user is an admin or the same user as the one being updated
//        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))
//                || id.toString().equals(authentication.getName())) {
//            user.setId(id); // Ensure the ID matches
//            User updatedUser = userService.updateUser(user);
//            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN); // Forbidden for unauthorized access
//        }
//    }
//
//    // Admin or Customer: Get User by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable Long id, Authentication authentication) {
//        // Check if the authenticated user is an admin or the same user as the one being fetched
//        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))
//                || id.toString().equals(authentication.getName())) {
//            Optional<User> user = userService.getUserById(id);
//            return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//        } else {
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN); // Forbidden for unauthorized access
//        }
//    }
//
//    // Admin: Get All Users
//    @GetMapping
//    public ResponseEntity<List<User>> getAllUsers() {
//        // Only allow Admin to get all users
//        List<User> users = userService.getAllUsers();
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }
//
//    // Admin or Customer: Delete User
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id, Authentication authentication) {
//        // Check if the authenticated user is an admin or the same user as the one being deleted
//        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))
//                || id.toString().equals(authentication.getName())) {
//            userService.deleteUser(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN); // Forbidden for unauthorized access
//        }
//    }
//
//    // Admin: Get Users by Role
//    @GetMapping("/role/{role}")
//    public ResponseEntity<List<User>> getUsersByRole(@PathVariable String role) {
//        List<User> users = userService.getUsersByRole(role);
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }
//}
