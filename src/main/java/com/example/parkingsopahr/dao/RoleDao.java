package com.example.parkingsopahr.dao;

import com.example.parkingsopahr.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDao extends JpaRepository<Role, String > {
    Optional<Role> findOneByRoleName(String roleName);
}
