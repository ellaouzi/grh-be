package com.fact.dto;

import lombok.Data;

import java.util.Date;

@Data
public class LotDto {
    private Long id;
    private String objet;
    private String reference;
    private double montant;
    private String designation;
    private String prestataire;
    private String contrat;
    private Long contrat_id;
    private Long prestataire_id;


}
