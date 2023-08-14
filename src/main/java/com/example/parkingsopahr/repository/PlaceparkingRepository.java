package com.example.parkingsopahr.repository;

import com.example.parkingsopahr.entities.placeparking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceparkingRepository extends JpaRepository<placeparking, Long> {
}
