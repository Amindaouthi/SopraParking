package com.example.parkingsopahr.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "placeparking")
public class placeparking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idplaceparking", nullable = false)
    private Long idpl;

    private String numero;

    private boolean occupee;

    private String type;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "parking_id")
    private parking parking;

    @OneToMany(mappedBy = "placeparking", cascade = CascadeType.ALL)
    private List<reservation> reservations;
}