package com.example.parkingsopahr.Controller;

import com.example.parkingsopahr.entities.JwtRequest;
import com.example.parkingsopahr.entities.JwtResponse;
import com.example.parkingsopahr.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class JwtController {
    @Autowired
    private JwtService jwtService ;

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception{
        return jwtService.createJwtToken(jwtRequest);
    }
}
