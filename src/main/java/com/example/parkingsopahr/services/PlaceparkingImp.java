package com.example.parkingsopahr.services;

import com.example.parkingsopahr.entities.parking;
import com.example.parkingsopahr.repository.ParkingRepository;
import com.example.parkingsopahr.repository.PlaceparkingRepository;
import com.example.parkingsopahr.entities.placeparking;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PlaceparkingImp implements Iplaceparking {

@Autowired
    PlaceparkingRepository placeparkingRepository ;
@Autowired
    ParkingRepository parkingRepository ;


    @Override
    public placeparking updatePlaceParking(Long idpl, placeparking pl ) {
        placeparking existinPlaceParking = placeparkingRepository.findById(idpl).orElseThrow(() ->new RuntimeException("Parking not found" + idpl));
        BeanUtils.copyProperties(pl , existinPlaceParking , "idpl");
        return  placeparkingRepository.save(existinPlaceParking);
    }


    @Override
    public Optional<placeparking> getPlaceParkingById(Long idpl) {
        return placeparkingRepository.findById(idpl);
    }

    
    @Override
    public List<placeparking> getAllPlaceParkings() {
        return  placeparkingRepository.findAll();
    }


    @Override
    public placeparking addPlaceParking(placeparking placeparking, Long idpa) {
        parking parking   = parkingRepository.findById(idpa).orElse(null);
        placeparking.setParking(parking);
        return placeparkingRepository.save(placeparking);
    }


    // public placeparking updatePlaceParking(Long idpl, placeparking placeparking) {
      //  placeparking existinPlaceParking = placeparkingRepository.findById(idpl).orElseThrow(() ->new RuntimeException("Parking not found" + idpl));
      //  BeanUtils.copyProperties(placeparking , existinPlaceParking , "idpl");
       // return  placeparkingRepository.save(existinPlaceParking);
   // }

    @Override
    public void deletePlaceParking(Long idpl) {

    }
    //public List<placeparking> searchAvailablePlaceParking(String location) {
   //     return placeparkingRepository.findByLocationAndOccupee(location, false);
   // }

   // public parking assignPlaceParkingToParking(Long idpa, String type, boolean occupee, String numero) {
    //    parking parking = parkingRepository.findById(idpa)
        //        .orElseThrow(() -> new EntityNotFoundException("Parking not found with ID: " + idpa));

       // placeparking placeParking  = placeparkingRepository.findById(idpl)
          //      .orElseThrow(() -> new EntityNotFoundException("PlaceParking not found with ID: " + idpl));
        // Assuming a collection of assignedPlaceParkings in Parking entity
       // placeParking.setParking(parking); // Assuming a parking property in PlaceParking entity

       // return parkingRepository.save(parking);
    //}

}
