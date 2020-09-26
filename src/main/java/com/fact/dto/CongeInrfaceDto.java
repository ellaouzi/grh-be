package com.fact.dto;

import java.util.Date;

public interface CongeInrfaceDto {
    Long getId();
    String getNom();
    String getPrenom();
    Date getdate_depart();
    Date getdate_retour();
    String getState();
    String getFullname();
    int getNonbre_jour();
    Long getInterim_id();
    String getInterim();
 }
