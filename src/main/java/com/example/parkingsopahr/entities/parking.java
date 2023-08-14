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
@Table(name = "Parking")
public class parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idparking", nullable = false)
    private Long idpa;
    private String name;
    private String address;
    private int capacity;

    @OneToMany(mappedBy = "parking", cascade = CascadeType.ALL)
    private List<placeparking> placeparkingList;
}