package com.customersphere.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements org.springframework.security.core.userdetails.UserDetails {

    private  String token;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @NotNull
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be valid")
    private String phone;

//    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private String role;

    // Default Constructor
    public User() {}

    // Parameterized Constructor
    public User(String token, Long id,String email, String password, String name, String phone, String role) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.role = role;

    }




    // Getters and Setters

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public  String  getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override

    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Adding "ROLE_" prefix to the role name as expected by Spring Security
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.role ));
    }

    @Override
    public String getUsername() {
        return this.email; // Email is used as the username for authentication
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Account is not expired
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Account is not locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Credentials are not expired
    }

    @Override
    public boolean isEnabled() {
        return true; // Account is enabled
    }

    // Role Enum for user roles
//    public enum Role {
//        ADMIN, CUSTOMER
//    }
}
