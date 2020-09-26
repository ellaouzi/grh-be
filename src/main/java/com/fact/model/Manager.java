package com.fact.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static org.hibernate.annotations.FetchMode.SELECT;

@Data
@Entity


public class Manager extends Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "manager", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
            private List<Employee> employees;




    //------------------------- Secure Logs---
    private Date created;
    private Date updated;
    private Long createdBy;
    private Long updatedBy;
    //------------------------- Secure Logs---




}