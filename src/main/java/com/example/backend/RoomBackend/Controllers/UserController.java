package com.example.backend.RoomBackend.Controllers;

import com.example.backend.RoomBackend.Entity.User;
import com.example.backend.RoomBackend.Repository.UserRepository;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173", 
             allowedHeaders = "*",
             allowCredentials = "true",
             methods = {RequestMethod.GET, RequestMethod.POST, 
                       RequestMethod.PUT, RequestMethod.DELETE, 
                       RequestMethod.OPTIONS})
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    @Transactional
    public ResponseEntity<Map<String, Object>> signup(@Valid @RequestBody User user) {
        Map<String, Object> response = new HashMap<>();
        
        if (userRepository.existsByEmail(user.getEmail())) {
            response.put("success", false);
            response.put("error", "EMAIL_EXISTS");
            response.put("message", "Email already registered");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        if (userRepository.existsByEmployeeId(user.getEmployeeId())) {
            response.put("success", false);
            response.put("error", "EMPLOYEE_ID_EXISTS");
            response.put("message", "Employee ID already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setIsApproved(false);

        User savedUser = userRepository.save(user);

        response.put("success", true);
        response.put("message", "Registration successful! Awaiting admin approval.");
        response.put("userId", savedUser.getId());
        
        return ResponseEntity.ok(response);
    }
}