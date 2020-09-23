package com.fact.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, name = "username")
    private String login;
    private String clearPassword;
    private String password;

    @OneToMany(fetch = FetchType.EAGER,
            mappedBy="utilisateur",
            cascade = CascadeType.ALL
           )
    private List<Authority> authorities;

    @JsonIgnore
    @ManyToMany
    private List<Role> roles;
    //------------------------- Secure Logs---
    private Date created;
    private Date updated;
    private Long createdBy;
    private Long updatedBy;
    //------------------------- Secure Logs---
}