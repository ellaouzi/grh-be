package com.fact.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity


public class Conge {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "utilisateur_id")
    private Employee employee;
    private int nonbre_jour;
    private Date dateDepart;
    private Date dateRetour;
    private int restAnneCourante;
    private int restAvantAnneCourant;
    private String statut;

}
