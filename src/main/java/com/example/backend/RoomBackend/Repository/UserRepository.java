package com.example.backend.RoomBackend.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.RoomBackend.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    

    boolean existsByEmail(String email);
    boolean existsByEmployeeId(String employeeId);
    Optional<User> findByEmail(String email);
}
