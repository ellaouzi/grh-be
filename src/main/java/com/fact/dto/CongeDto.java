package com.fact.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CongeDto {
    private Long id;
    private int nonbre_jour;
    private Date dateDepart;
    private Date dateRetour;
    private int restAnneCourante;
    private int restAvantAnneCourant;
    private String statut;


}
