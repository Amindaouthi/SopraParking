package com.example.parkingsopahr.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "User")
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser", nullable = false)
    private Long iduser;

    private String nom;

    private String email;

    private String motDePasse;

    @Enumerated
    roleuser roleuser;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<reservation> reservations;
}