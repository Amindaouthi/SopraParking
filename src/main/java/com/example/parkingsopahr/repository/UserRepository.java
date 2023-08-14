package com.example.parkingsopahr.repository;

import com.example.parkingsopahr.entities.user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<user, Long> {
}
