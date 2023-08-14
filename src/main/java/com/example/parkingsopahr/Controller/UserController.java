package com.example.parkingsopahr.Controller;

import com.example.parkingsopahr.entities.user;
import com.example.parkingsopahr.entities.reservation;
import com.example.parkingsopahr.services.ReservationImp;
import com.example.parkingsopahr.services.UserImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
   UserImp userImp ;
    @Autowired
    ReservationImp reservationImp ;

    @GetMapping("GetAllUsers")
    public List<user> getAllUsers() {
        return userImp.getAllUsers();
    }

    @GetMapping("GetUser/{iduser}")
    public user getUserById(@PathVariable Long iduser) {
        return userImp.getUserById(iduser).orElse(null);
    }


    @PostMapping("/addUser")
    public user adduser(@RequestBody user user ) {
        return userImp.adduser(user);
    }

    @PutMapping("updateUser/{iduser}")
    public ResponseEntity<user> updateUser(@RequestBody user u, @PathVariable("iduser") Long iduser) {
        user updatedEntity = userImp.updateUser(iduser,u);
        return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
    }


    @DeleteMapping("DeleteUser/{iduser}")
    public void deleteUser(@PathVariable Long iduser) {
        userImp.deleteUser(iduser);
    }
}
