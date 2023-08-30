package com.example.parkingsopahr.Controller;

import com.example.parkingsopahr.entities.reservation;
import com.example.parkingsopahr.services.PlaceparkingImp;
import com.example.parkingsopahr.services.ReservationImp;
import com.example.parkingsopahr.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    ReservationImp reservationImp;
    @Autowired
    UserService userImp ;
    @Autowired
    PlaceparkingImp placeparkingImp;

    @GetMapping("GetAllReservation")
    List<reservation> getAllReservation(reservation r) {
        return reservationImp.getAllReservation(r);
    }

    @GetMapping("GetReservation/{idReservation}")
    public reservation getReservationById(@PathVariable Long idReservation) {
        return reservationImp.getReservationById(idReservation);
    }

    @PostMapping("addReservation/{iduser}/{idpl}")
    public reservation addreservation(@RequestBody reservation reservation , @PathVariable ("userEmail")String userEmail ,
                                  @PathVariable ("idpl")Long idpl) {
        return reservationImp.addreservation(reservation , userEmail , idpl);
   }
    @PutMapping("updateReservation/{idReservation}")
    public ResponseEntity<reservation> updatereservation(@RequestBody reservation r, @PathVariable("idReservation") Long idReservation) {
        reservation updatedEntity = reservationImp.updatereservation(idReservation, r);
        return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
    }

    @DeleteMapping("deleteReservation/{idReservation}")
    public void deleteReservation(@PathVariable Long idReservation) {
        reservationImp.deleteReservation(idReservation);
    }

    @PostMapping("/affecter")
    public ResponseEntity<reservation> affecterReservation(@RequestBody reservation ReservationDto ) {
        try {
        reservation reservation  = reservationImp.affecterReservation(
                ReservationDto.getUser().getUserEmail(),
                ReservationDto.getPlaceparking().getIdpl(),
                ReservationDto.getDateDebut(),
                ReservationDto.getDateFin()
            );
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/create-with-priority")
    public ResponseEntity<reservation> createReservationWithPriority(
            @RequestBody Map<String, Object> requestBody) {
        try {
            String  userEmail = (requestBody.get("userEmail").toString());
            Long idpl = Long.parseLong(requestBody.get("idpl").toString());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date dateDebut = dateFormat.parse(requestBody.get("dateDebut").toString());
            Date dateFin = dateFormat.parse(requestBody.get("dateFin").toString());
            reservation reservation = reservationImp.createReservationWithPriority(
                    userEmail,
                    idpl,
                    dateDebut,
                    dateFin
            );

            return ResponseEntity.ok(reservation);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }







       /* @GetMapping("/confirmReservation/{idReservation}")
        public ResponseEntity<String> confirmReservation(@PathVariable Long idReservation) {

            reservation reservation = reservationImp.getReservationById(idReservation);

            if (reservation == null) {
                return ResponseEntity.notFound().build();
            }

            // Perform reservation confirmation
            reservationImp.confirmReservation(reservation);

            return ResponseEntity.ok("Reservation confirmed and email notification sent.");
        }*/
    }





