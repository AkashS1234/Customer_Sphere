package com.customersphere.services;



import com.customersphere.entity.User;
import com.customersphere.repository.UserRepository;
import com.customersphere.security.UserDetailsServiceImpl;
import com.customersphere.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtUtils;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    // Register a new user
    public User user(String username, String password, String role, String phone, String email) {
        User user = new User();
        user.setName(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        user.setPhone(phone);
        user.setEmail(email);
        return userRepository.save(user);
    }

    public User createUser(User user) {
        // Encrypt the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    // Authenticate user and fetch details with JWT
    public User loginAndFetchDetails(String email, String password) {
        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        // Fetch user from the database
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Generate JWT token
        String token = jwtUtils.generateToken(user.getEmail());

        // Return user details along with the token
        return new User(token,user.getId(), user.getEmail(),user.getPassword(),user.getName(), user.getPhone(), user.getRole() );
    }

    public void user() {
    }
}


















//package com.customersphere.services;
//
//import com.customersphere.entity.User;
//import com.customersphere.repository.UserRepository;
//import com.customersphere.security.UserDetailsServiceImpl;
//import com.customersphere.utils.JwtTokenUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.Optional;
//
//
//
//@Service
//public class AuthService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private JwtTokenUtil jwtUtils;
//
//    @Autowired
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public AuthService(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//
//    // Register a new user
//    public User registerUser(String username, String password, String role, String phone, String email) {
//        User user = new User();
//        user.setName(username);
//        user.setPassword(passwordEncoder.encode(password));
//        user.setRole(role);
//        user.setPhone(phone);
//        user.setEmail(email);
//        return userRepository.save(user);
//    }
//
//    // Authenticate user and generate JWT
//    public String loginUser(String email, String password) {
//        // Authenticate user using AuthenticationManager
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(email, password));
//
//        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
//
//        // Generate JWT token
//        return jwtUtils.generateToken(userDetails.getUsername());
//    }
//}











//@Service
//public class AuthService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private JwtTokenUtil jwtUtils;
//
//    @Autowired
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public AuthService(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }
//
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserDetailsServiceImpl UserDetailsService;
//
//    // Register a new user
//    public User registerUser(String username, String password, String role, String phone, String email) {
////        if (userRepository.findByName(username) != null) {
////            throw new RuntimeException("User already exists!");
////        }
//
//        User user = new User();
//        user.setName(username);
//        user.setPassword(passwordEncoder.encode(password));
//        user.setRole(role);
//        user.setPhone(phone);
//        user.setEmail(email);
//        return userRepository.save(user);
//    }
//
//    // Authenticate user and generate JWT
//    public String loginUser(String name, String password) {
//        // Authenticate user using AuthenticationManager
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(name, password));
//
//        UserDetails userDetails = UserDetailsService.loadUserByUsername(name);
//
//        // Generate JWT token
//        return jwtUtils.generateToken(userDetails.getUsername());
//    }
//}











//@Service
//public class AuthService extends UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    // Register a new user (Admin or Customer)
//    public User register(User user) {
//        // Check if the email already exists
//        if (userRepository.existsByEmail(user.getEmail())) {
//            throw new IllegalArgumentException("Email is already in use");
//        }
//
//        // Encrypt the password before saving
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        // Default role is CUSTOMER unless specified
//        if (user.getRole() == null) {
//            user.setRole(User.Role.CUSTOMER);
//        }
//
//        return userRepository.save(user);
//    }
//
////     Login functionality: Authenticate user by email and password
//public User login(String email, String password) {
//    try {
//        // Create authentication token with email and password
//        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
//
//        // Authenticate the user using AuthenticationManager
//        authentication = authenticationManager.authenticate(authentication);
//
//        // If authentication is successful, fetch the user details from the repository
//        return userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//    } catch (BadCredentialsException e) {
//        throw new BadCredentialsException("Invalid username or password");
//    }
//}
//
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getAuthorities());
//    }
//}


