package com.example.parkingsopahr.services;

import com.example.parkingsopahr.entities.reservation;

import java.util.Date;
import java.util.List;

public interface IReservation {
    List<reservation> getAllReservation(reservation r );

    reservation getReservationById(Long idReservation);

    reservation addreservation(reservation reservation , Long iduser , Long idpl);

    reservation updatereservation(Long idReservation , reservation updatereservation);

    void deleteReservation(Long id);
    reservation affecterReservation(Long iduser, Long idpl, Date dateDebut, Date dateFin);
    int calculatePriority(String userPosition) ;
    //void confirmReservation(reservation reservation);
}

