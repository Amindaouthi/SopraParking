package com.example.parkingsopahr.Controller;

import com.example.parkingsopahr.entities.parking;
import com.example.parkingsopahr.entities.placeparking;
import com.example.parkingsopahr.entities.reservation;
import com.example.parkingsopahr.services.ParkingImp;
import com.example.parkingsopahr.services.PlaceparkingImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Placeparking")
public class PlaceParkingController {
    @Autowired
    private PlaceparkingImp placeparkingImp;
    @Autowired
    private ParkingImp parkingImp ;

    @GetMapping("GetAllPlaceParking")
    public List<placeparking> getAllPlaceParking() {
        return placeparkingImp.getAllPlaceParkings();
    }

    @GetMapping("GetPlaceParking/{idpl}")
    public placeparking getPlaceParkingById(@PathVariable Long idpl) {
        return placeparkingImp.getPlaceParkingById(idpl).orElse(null);
    }

    @PostMapping("/addPlaceParking/{idpa}")
    public placeparking addPlaceParking(@RequestBody placeparking p,
                                        @PathVariable ("idpa")Long idpa) {
        return placeparkingImp.addPlaceParking(p,idpa);
    }

    @PutMapping("updatePlaceParking/{idpl}")
    public ResponseEntity<placeparking> updatePlaceparking(@RequestBody placeparking pl ,  @PathVariable("idpl") Long idpl) {
        placeparking updatedEntity = placeparkingImp.updatePlaceParking(idpl,pl);
        return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
    }
    @DeleteMapping("DeletePlaceParking/{idpl}")
    public void deleteParking(@PathVariable Long idpl) {
        placeparkingImp.deletePlaceParking(idpl);
    }

    }
