package com.example.parkingsopahr.services;

import com.example.parkingsopahr.entities.user;

import java.util.List;
import java.util.Optional;

public interface Iuser {
    List<user> getAllUsers();

    Optional<user> getUserById(Long iduser);

    user adduser(user user);

    user updateUser(Long iduser, user updatedUser);

    void deleteUser(Long iduser);

}
