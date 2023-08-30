package com.example.parkingsopahr.repository;

import com.example.parkingsopahr.entities.placeparking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceparkingRepository extends JpaRepository<placeparking, Long> {
    //List<placeparking> findByLocationAndOccupee(String location, boolean occupee);


}
