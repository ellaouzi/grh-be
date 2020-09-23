package com.fact.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity

public class Employee extends Utilisateur {

    private String code;
    private boolean enabled;
    private Date lastLogin;
    private String status;
    private String nom;
    private String matricule;
    private String prenom;
    private String email;
    private String type;
    private String gsm;
    private String fixe;
    private Date naissance;
    private Date dateRecrutement;
    private boolean isFonctionnaire;
     private String login;
    private String clearPassword;
    private String password;
    @Transient
    private Long manager_id_;
    @Transient
    private Long interim_id_;
    @Transient
    private Long entite_id_;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entite_id")
    private Entite entite;



    @Transient
    private Long fonction_id_;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fonction_id")
    private Fonction fonction;



    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Manager manager;



    @OneToOne
    @JoinColumn(name="interim_id")
    private Employee interim;

    @OneToOne(mappedBy="interim")
    private Employee remplacement;


    //------------------------- Secure Logs---
    private Date created;
    private Date updated;
    private Long createdBy;
    private Long updatedBy;
    //------------------------- Secure Logs---




}