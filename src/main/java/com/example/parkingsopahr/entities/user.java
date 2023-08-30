package com.example.parkingsopahr.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class user implements Serializable {

    @Id
    private String userEmail;
    private String userFirstName;
    private String userLastName;
    private String userPassword;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id")
    private Role role;


    //
    @OneToMany(mappedBy = "user")
    private List<reservation> reservations;
}