package com.fact.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity


public class Fonction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    private String nom;
    @JsonIgnore
    @OneToMany(mappedBy = "fonction")
    private List<Employee> employees;

    //------------------------- Secure Logs---
    private Date created;
    private Date updated;
    private Long createdBy;
    private Long updatedBy;
    //------------------------- Secure Logs---




}