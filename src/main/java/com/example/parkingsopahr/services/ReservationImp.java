package com.example.parkingsopahr.services;

import com.example.parkingsopahr.repository.PlaceparkingRepository;
import com.example.parkingsopahr.repository.ReservationRepository;
import com.example.parkingsopahr.repository.UserRepository;
import com.example.parkingsopahr.entities.placeparking;
import com.example.parkingsopahr.entities.reservation;
import com.example.parkingsopahr.entities.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ReservationImp implements IReservation {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PlaceparkingRepository placeparkingRepository;

    @Autowired
    public ReservationImp(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<reservation> getAllReservation(reservation r) {
        return reservationRepository.findAll();
    }

    @Override
    public reservation getReservationById(Long idReservation) {
        return reservationRepository.findById(idReservation).orElse(null);
    }

    @Override
    public reservation addreservation(reservation reservation , Long iduser , Long idpl) {
        user user = userRepository.findById(iduser).orElse(null);
        placeparking place = placeparkingRepository.findById(idpl).orElse(null);
        reservation.setPlaceparking(place);
        reservation.setUser(user);
        return reservationRepository.save(reservation);
    }

    @Override
    public reservation updatereservation(Long idReservation, reservation r) {
        reservation existinReservation = reservationRepository.findById(idReservation).orElseThrow(() -> new RuntimeException("Parking not found" + idReservation));
        BeanUtils.copyProperties(r, existinReservation, "idReservation");
        return reservationRepository.save(existinReservation);
    }

    @Override
    public void deleteReservation(Long idReservation) {
        reservationRepository.deleteById(idReservation);
    }

    @Override
    public reservation affecterReservation(Long iduser, Long idpl, Date dateDebut, Date dateFin) {
        user user = userRepository.findById(iduser).orElse(null);
        placeparking placeparking = placeparkingRepository.findById(idpl).orElse(null);

        if (user == null || placeparking == null) {

            throw new IllegalArgumentException("Utilisateur ou place de parking introuvable.");
        }
        reservation reservation = new reservation();
        reservation.setUser(user);
        reservation.setPlaceparking(placeparking);
        reservation.setDateDebut(dateDebut);
        reservation.setDateFin(dateFin);

        int priority = calculatePriority(String.valueOf(user.getRoleuser()));
        reservation.setPriority(priority);

        return reservationRepository.save(reservation);

    }
    public int calculatePriority(String roleuser) {
        // You can implement a logic here to assign priority based on user position
        // For example:
        if (roleuser.equals("manager")) {
            return 3; // Highest priority
        } else if (roleuser.equals("team leader")) {
            return 2;
        } else {
            return 1; // Lowest priority
        }
    }
    /*
    @Override
    public void confirmReservation(reservation reservation) {
        String to = reservation.getUser().getEmail();
        String subject = "Reservation Confirmed";
        String content = "Your reservation for parking place #" + reservation.getPlaceparking().getIdpl() + " has been confirmed. Enjoy your parking!";
        mailService.sendNotification(to, subject, content);
    } */
}








