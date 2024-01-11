package com.tp.etronSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Offre {
    @Id
    private String id;
    private float fraisMensuel;
    private float fraisAC;
    private float fraisDC;
    private float fraisHautPuissance;
    private float fraisBlocageAC;
    private float fraisBolcageDC;
    private int dureeContrat; //en mois

}
