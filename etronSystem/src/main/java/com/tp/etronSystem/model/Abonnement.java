package com.tp.etronSystem.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Abonnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String emailClient;
    private String IdOffre;
    private LocalDate dateDebut;
    private LocalDate dateFin;

}
