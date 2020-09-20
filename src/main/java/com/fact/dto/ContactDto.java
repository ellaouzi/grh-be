package com.fact.dto;

import lombok.Data;

@Data
public class ContactDto {
    private Long id;
    private String denominationSociale;
    private String cin;
    private String nom;
    private String prenom;
    private String email;
    private String fax;
    private String fixe;
    private String adresse;
    private String ville;
}
