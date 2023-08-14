package com.example.parkingsopahr.services;

import com.example.parkingsopahr.entities.parking;
import com.example.parkingsopahr.entities.placeparking;

import java.util.List;
import java.util.Optional;

public interface Iplaceparking {

    Optional<placeparking> getPlaceParkingById(Long idpl);


    List<placeparking> getAllPlaceParking();

    List<placeparking> getAllPlaceParkings();



    placeparking updatePlaceParking(Long idpl, placeparking pl);

    //public parking assignPlaceParkingToParking(Long idpa, Long idpl);


    placeparking addPlaceParking(placeparking placeparking, Long idpa);

    void deletePlaceParking(Long idpl);
}
