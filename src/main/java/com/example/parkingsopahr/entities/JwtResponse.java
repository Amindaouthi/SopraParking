package com.example.parkingsopahr.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
    private  user user ;
    private  String jwtToken ;

    public JwtResponse( user user , String jwtToken){
        this.user = user ;
        this.jwtToken = jwtToken ;
    }
}
