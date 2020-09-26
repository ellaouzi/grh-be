package com.fact.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
 // @Table(name = "table_name", uniqueConstraints={@UniqueConstraint(columnNames = "column1"),@UniqueConstraint(columnNames = "column2")})
@Table(name = "utilisateur", uniqueConstraints={@UniqueConstraint(columnNames = "username")})

public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
     @Column(unique = true, name = "username")
    private String login;
    private String clearPassword;
    private String password;
    @Column(name = "enabled", columnDefinition = "boolean default false")
    private boolean enabled;

    private String matricule;
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