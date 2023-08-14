package com.example.parkingsopahr.repository;

import com.example.parkingsopahr.entities.parking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepository extends JpaRepository<parking, Long> {
}
