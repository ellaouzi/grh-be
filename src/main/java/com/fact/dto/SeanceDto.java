package com.fact.dto;

import lombok.Data;

@Data
public class SeanceDto {
    private Long id;
    private String seanceDu;
    private String jour;
    private String seanceAu;
    private String genre;
    private String doucheDu;
    private String doucheAu;

    private String descipline;
    private int delai;
    private int doucheDuree;
}
