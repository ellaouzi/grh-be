package com.fact.model;


import lombok.Data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Data
@Table(name="salarier")
public class Salarier {

    @Id
    @Column(name="EMPLOYEE_ID")
    @GeneratedValue
    private Long salarierId;

    @Column(name="FIRSTNAME")
    private String firstname;

    @Column(name="LASTNAME")
    private String lastname;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="chef_id")
    private Salarier chef;

    @OneToMany(mappedBy="chef")
    private Set<Salarier> subordinates = new HashSet<Salarier>();

    public Salarier() {
    }

    public Salarier(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    // Getter and Setter methods
}