package com.example.mlms.repository;

import com.example.mlms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    // Additional query methods if needed
}
