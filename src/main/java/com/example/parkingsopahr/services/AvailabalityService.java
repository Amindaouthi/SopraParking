package com.example.parkingsopahr.services;

import com.example.parkingsopahr.entities.reservation;
import com.example.parkingsopahr.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class AvailabalityService {
    @Autowired
    private ReservationRepository reservationRepository;

    public boolean isParkingSpotAvailable(Long idpa, Date startDate, Date endDate) {
        List<reservation> conflictingReservations = reservationRepository.findConflictingReservations(idpa, startDate, endDate);
        return conflictingReservations.isEmpty();
    }
}
