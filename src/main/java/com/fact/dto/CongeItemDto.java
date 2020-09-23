package com.fact.dto;

import java.util.Date;

public interface CongeItemDto {
    Long getId();
    Date getdate_depart();
    Date getdate_retour();
    String getState();
    String getFullname();
    int getNonbre_jour();
    Long getUtilisateur_id();

 }
