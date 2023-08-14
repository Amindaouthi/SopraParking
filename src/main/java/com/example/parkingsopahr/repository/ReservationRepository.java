package com.example.parkingsopahr.repository;

import com.example.parkingsopahr.entities.reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<reservation, Long> {
    @Query("SELECT r FROM reservation r " +
            "WHERE r.placeparking.idpl = :placeParkingId " +
            "AND r.dateDebut <= :endDate " +
            "AND r.dateFin >= :startDate")
    List<reservation> findConflictingReservations(
            @Param("placeParkingId") Long idpl,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);
}
