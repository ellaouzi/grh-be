package com.fact.model;

import com.fact.enums.CongeState;
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
    private Utilisateur employee;
    @Transient
    private Long employee_id;
    private int nonbre_jour;
    private Date dateDepart;
    private Date dateRetour;
    @Enumerated(EnumType.STRING)
    private CongeState state;
    private int restAnneCourante;
    private int restAvantAnneCourant;
    private String statut;
    private Date created;
    private Date updated;


}
