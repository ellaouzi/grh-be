package com.fact.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FactureDto {
    private Long id;
    private String code;
    private String numero;
    private Date dateDepot;
    private double montant;
    private Date datePvRecep;
    private String statut; // deposee, envoyee pour paiement, reglee
    private String prestataire;
    private String contrat;
    private Long contrat_id;
    private Long prestataire_id;
    private String statut_utilisateur; // deposee, envoyee pour paiement, reglee

}
