package com.customersphere.controllers;


import com.customersphere.entity.User;
import com.customersphere.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Register new user
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        authService.user(
                user.getName(),
                user.getPassword(),
                user.getRole(),
                user.getPhone(),
                user.getEmail());

        return ResponseEntity.ok("User registered successfully");
    }

    // Login user and generate JWT
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        User response = authService.loginAndFetchDetails(user.getEmail(),user.getPassword());
        return ResponseEntity.ok(response);
    }
}














//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    private final AuthService authService;
//
//    public AuthController(AuthService authService) {
//        this.authService = authService;
//    }
//
//    // Register new user
//    @PostMapping("/register")
//    public ResponseEntity<String> register(
//            @RequestParam String name,
//            @RequestParam String password,
//            @RequestParam String role,
//            @RequestParam String phone,
//            @RequestParam String email) {
//        authService.registerUser(name, password, role, phone, email);
//        return ResponseEntity.ok("User registered successfully");
//    }
//
//    // Login user and generate JWT
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
//        String jwt = authService.loginUser(email, password);
//        return ResponseEntity.ok("Bearer " + jwt);
//    }
//}










//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//
//private final AuthService authService;
//
//    public AuthController(AuthService authService) {
//        this.authService = authService;
//    }
//
//    // Register new user
//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestParam String name, @RequestParam String password,  @RequestParam String role,@RequestParam String phone, @RequestParam String email) {
//        User user = authService.registerUser(name, password, role,phone,email);
//        return ResponseEntity.ok("User registered successfully");
//    }
//
//    // Login user and generate JWT
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestParam String name, @RequestParam String password) {
//        String jwt = authService.loginUser(name, password);
//        return ResponseEntity.ok("Bearer " + jwt);
//    }
//}
//








//    @Autowired
//    private AuthService authService;
//
//    // Register a new user (Admin or Customer)
//    @PostMapping("/register")
//    public ResponseEntity<User> register(@RequestBody User user) {
//        try {
//            User registeredUser = authService.register(user);
//            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    // Login functionality: Authenticate user by email and password
//    @PostMapping("/login")
//    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
//        try {
//            User user = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        } catch (BadCredentialsException e) {
//            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
//        } catch (UsernameNotFoundException e) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }
//}