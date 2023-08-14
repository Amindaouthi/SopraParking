package com.example.parkingsopahr.services;

import com.example.parkingsopahr.repository.ReservationRepository;
import com.example.parkingsopahr.repository.UserRepository;
import com.example.parkingsopahr.entities.user;
import com.example.parkingsopahr.entities.reservation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserImp implements Iuser{

    @Autowired
    private UserRepository userRepository ;
    @Autowired
    private ReservationRepository reservationRepository;

    public UserImp(UserRepository UserRepository){this.userRepository=UserRepository;}


    @Override
    public List<user> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<user>getUserById(Long iduser) {
        return userRepository.findById(iduser);
    }

    @Override
    public user adduser(user user) {

        return userRepository.save(user);
    }

    @Override
    public user updateUser(Long iduser, user u ) {
        user existinuser = userRepository.findById(iduser).orElseThrow(() ->new RuntimeException("Parking not found" + iduser));
        BeanUtils.copyProperties(u , existinuser , "iduser");
        return  userRepository.save(existinuser);
    }


    @Override
    public void deleteUser(Long idUser) {
        userRepository.deleteById(idUser);
    }
}
