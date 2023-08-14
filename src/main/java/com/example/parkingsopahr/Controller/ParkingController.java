package com.example.parkingsopahr.Controller;

import com.example.parkingsopahr.entities.parking;
import com.example.parkingsopahr.services.ParkingImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parkings")
public class ParkingController {
    @Autowired
    private ParkingImp parkingimp;
    //http://localhost:8082/test/parkings/Getallparkings
    @GetMapping("Getallparkings")
    public List<parking> getAllParkings() {
        return parkingimp.getAllParkings();
    }

    @GetMapping("Getparking/{id}")
    public parking getParkingById(@PathVariable Long id) {
        return parkingimp.getParkingById(id).orElse(null);
    }

    //http://localhost:8083/parkings/addparking
    @PostMapping("/addparking")
    public parking addParking(@RequestBody parking parking) {
        return parkingimp.addParking(parking);
    }
// http://localhost:8082/test/parkings/updateparking/{{id}}

@PutMapping("updateparking/{idpa}")
public ResponseEntity<parking> updateParking(@RequestBody parking p, @PathVariable("idpa") Long idpa) {
    parking updatedEntity = parkingimp.updateParking(idpa, p);
    return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
}


    @DeleteMapping("Deleteparking/{idpa}")
    public void deleteParking(@PathVariable Long idpa) {
        parkingimp.deleteParking(idpa);
    }
}

