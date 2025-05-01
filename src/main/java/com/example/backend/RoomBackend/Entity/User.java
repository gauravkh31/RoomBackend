package com.example.backend.RoomBackend.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Employee name is required")
    @Column(nullable = false)
    private String employeeName;

    @NotBlank(message = "Employee ID is required")
    @Pattern(regexp = "^[A-Z]{2,5}-\\d{3,6}$", message = "Employee ID must be in format DEPT-123")
    @Column(unique = true, nullable = false)
    private String employeeId; // e.g., "FIN-123"

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Pattern(regexp = ".+@nic\\.in$", message = "Only official @nic.in emails allowed")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "Department is required")
    @Column(nullable = false)
    private String department; // Values: "finance", "it", "health", "admin"

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Indian mobile number")
    @Column(nullable = false)
    private String mobile; // Changed from mobileNumber to match React form

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean isApproved = false;

    // Removed profile photo fields since they're not in the React form
    // Add these back if you implement profile photos later

    // Additional fields you might want to add:
    // @CreationTimestamp
    // private LocalDateTime createdAt;
    
    // @UpdateTimestamp
    // private LocalDateTime updatedAt;
}