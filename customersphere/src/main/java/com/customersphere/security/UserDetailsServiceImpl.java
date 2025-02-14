package com.customersphere.security;
import com.customersphere.entity.User;
import com.customersphere.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

@Configuration
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Fetch the user from the database using the email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found "));

        // Return an instance of org.springframework.security.core.userdetails.User
        // which is a specific implementation of UserDetails
        return  new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getAuthorities()) ; // Assign the role of the user


    }



    // This method loads the user by email and constructs a UserDetails object
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        // Fetch the user from the database using the email
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
//
//        // Return an instance of org.springframework.security.core.userdetails.User
//        // which is a specific implementation of UserDetails
//        return org.springframework.security.core.userdetails.User
//                .withUsername(user.getEmail())
//                .password(user.getPassword())
//                .roles(user.getRole())  // Assign the role of the user
//                .build();
//    }
}









//@Service
//public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    // This method loads the user by email and constructs a UserDetails object
//    @Override
//    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
//        // Fetch the user from the database using the username (email)
//        User user = userRepository.findByName(name)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + name));
//
//        // Return an instance of org.springframework.security.core.userdetails.User
//        // which is a specific implementation of UserDetails
//        UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(user.getUsername());
//        builder.password(user.getPassword());
//        builder.roles(user.getRole());  // Assign the role of the user
//
//        // Return the UserDetails object with the necessary user info
//        return builder.build();
//    }
//}
