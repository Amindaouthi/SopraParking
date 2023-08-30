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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<reservation>getAllReservation(reservation r) {
        return reservationRepository.findAll();
    }

    @Override
    public reservation getReservationById(Long idReservation) {
        return reservationRepository.findById(idReservation).orElse(null);
    }

    @Override
    public reservation addreservation(reservation reservation , String userEmail , Long idpl) {
        user user = userRepository.findById(userEmail).orElse(null);
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
    public reservation affecterReservation(String userEmail,  Long Idpl ,  Date dateDebut, Date dateFin) {
        return null;
    }

    @Override
    public reservation createReservationWithPriority(String userEmail, Long idpl, Date dateDebut, Date dateFin) {
        user user = userRepository.findById(userEmail).orElse(null);
        placeparking placeparking = placeparkingRepository.findById(idpl).orElse(null);

        if (user == null || placeparking == null) {

            throw new IllegalArgumentException("Utilisateur ou place de parking introuvable.");
        }

        reservation reservation = new reservation();
        reservation.setUser(user);
        reservation.setPlaceparking(placeparking);
        reservation.setDateDebut(dateDebut);
        reservation.setDateFin(dateFin);

       int priority = calculatePriority(user.getRole().getRoleName().toString());
      reservation.setPriority(priority);


        return reservationRepository.save(reservation);

    }

    @Override
    public ResponseEntity<reservation> createReservationWithPriority(
            @RequestBody Map<String, Object> requestBody) {
        try {
            String userEmail = (requestBody.get("userEmail").toString());
            Long idpl = Long.parseLong(requestBody.get("idpl").toString());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date dateDebut = dateFormat.parse(requestBody.get("dateDebut").toString());
            Date dateFin = dateFormat.parse(requestBody.get("dateFin").toString());

            user user = userRepository.findById(userEmail).orElse(null);
            placeparking placeparking = placeparkingRepository.findById(idpl).orElse(null);

            if (user == null || placeparking == null) {
                throw new IllegalArgumentException("Utilisateur ou place de parking introuvable.");
            }

            // Check if the selected dates are telecommuting days or holidays
            /*boolean isTelecommutingDay = isTelecommutingDay(dateDebut) && isTelecommutingDay(dateFin);
            boolean isHoliday = isHoliday(dateDebut) && isHoliday(dateFin);

            if (isTelecommutingDay || isHoliday) {
                return ResponseEntity.badRequest().body("Parking space not available on this day.");
            }*/

            reservation reservation = new reservation();
            reservation.setUser(user);
            reservation.setPlaceparking(placeparking);
            reservation.setDateDebut(dateDebut);
            reservation.setDateFin(dateFin);

            int priority = calculatePriority(user.getRole().getRoleName().toString());
            reservation.setPriority(priority);

            return ResponseEntity.ok(reservationRepository.save(reservation));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public int calculatePriority(String roleUser) {
        // Convert roleUser to lowercase for case-insensitive comparison
        String lowercaseRole = roleUser.toLowerCase();

        // Create a map to store role priorities
        Map<String, Integer> rolePriorities = new HashMap<>();
        rolePriorities.put("manager", 1);
        rolePriorities.put("teamleader", 2);
        rolePriorities.put("consultant", 3);

        // Get the priority from the map, or return a default priority if not found
        return rolePriorities.getOrDefault(lowercaseRole, 0);
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








