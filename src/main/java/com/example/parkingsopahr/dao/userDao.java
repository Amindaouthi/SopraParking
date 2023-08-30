package com.example.parkingsopahr.dao;

import com.example.parkingsopahr.entities.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface userDao extends JpaRepository<user, String> {
    Optional <user> findOneByUserFirstName(String userFirstName) ;

}

