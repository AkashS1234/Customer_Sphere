package com.customersphere.repository;

import com.customersphere.entity.User;
import com.customersphere.entity.User.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find a user by email
    Optional<User> findByEmail(String email);

    // Find users by role
    List<User> findByRole(Role role);

    // Check if a user exists by email
    boolean existsByEmail(String email);

    // Find a user by ID (This is inherited from JpaRepository)
    Optional<User> findById(Long id);
}
