package com.example.parkingsopahr.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Reservation")
public class reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReservation", nullable = false)
    private Long idReservation;
    private Date dateDebut;
    private Date dateFin;
    private int priority;

    // liaison with user
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "userid")
    private user user;
    // liaison with placeParking
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "placeparking_id")
    private placeparking placeparking;
}

