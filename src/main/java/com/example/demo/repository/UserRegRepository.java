package com.example.demo.repository;

import com.example.demo.models.entities.UserReg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRegRepository extends JpaRepository<UserReg,Long> {
    UserReg findByUsername(String username);
}
