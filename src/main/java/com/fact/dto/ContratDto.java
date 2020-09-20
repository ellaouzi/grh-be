package com.fact.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ContratDto {
    private Long id;
    private String objet;
    private String designation;
    private String code;
    private Date timeAu;
    private String reference;
    private String exercice;
    private String annee;
    private Date dateEcheance;
    private double montant;
    private boolean enabled;
    private boolean alloti;
    private String typePrestataire;
    private String typeContrat;
    private Long contrat_id;
    private Long prestataire_id;
}
