
package com.fact.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity


public class Logtrace {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    @Column(length = 400)
    private String task;
    private Date created;
    private Long createdBy;
    //------------------------- Secure Logs---




}