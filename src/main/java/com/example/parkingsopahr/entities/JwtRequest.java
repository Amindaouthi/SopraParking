package com.example.parkingsopahr.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class JwtRequest implements Serializable {
    private String userEmail;
    private String userPassword;

}
