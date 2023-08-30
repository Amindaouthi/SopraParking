package com.example.parkingsopahr.services;

import com.example.parkingsopahr.entities.reservation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IReservation {
    List<reservation> getAllReservation(reservation r );

    reservation getReservationById(Long idReservation);

    reservation addreservation(reservation reservation , String userEmail , Long idpl);

    reservation updatereservation(Long idReservation , reservation updatereservation);

    void deleteReservation(Long id);
    reservation affecterReservation(String userEmail, Long Idpl, Date dateDebut, Date dateFin);
   // int calculatePriority(String userPosition) ;

    public reservation createReservationWithPriority(String userEmail, Long idpl, Date dateDebut, Date dateFin);

    ResponseEntity<reservation> createReservationWithPriority(
            @RequestBody Map<String, Object> requestBody);
    //void confirmReservation(reservation reservation);
}

