package com.example.parkingsopahr.Controller;

import com.example.parkingsopahr.entities.ApiResponse;
import com.example.parkingsopahr.entities.user;
import com.example.parkingsopahr.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping({"/RoleAndUser"}) //////////////////////USELESS////////////////////////
    public void initRoleAndUser() {
        userService.initRolesAndUser();
    }


    @PostMapping({"/registerNewUser"})
    public ResponseEntity<user> registrerNewUser(@RequestBody user user) {
        Integer test = userService.registrerNewUser(user);
        ApiResponse response = new ApiResponse();
        if(test == 1){
            response.setMessage("user registred successffuly");
            return new ResponseEntity(response , HttpStatus.OK);
        }
        else{
            response.setMessage("cannot register user");
            return new ResponseEntity(response, HttpStatus.CONFLICT);
        }

    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin() {
        return "this is for Admin";
    }


    @GetMapping({"/forManager"})
    @PreAuthorize("hasRole('Manager')")
    public String forUser() {
        return "this is for Manager";

    }

}

