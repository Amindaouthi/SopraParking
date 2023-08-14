package com.example.parkingsopahr.services;

import com.example.parkingsopahr.entities.parking;

import java.util.List;
import java.util.Optional;

public interface Iparking {
    List<parking> getAllParkings();

    Optional<parking> getParkingById(Long idpa);

    parking addParking(parking parking);

    parking updateParking(Long id, parking updatedParking);

    void deleteParking(Long id);
}
