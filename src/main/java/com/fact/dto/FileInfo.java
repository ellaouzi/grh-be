package com.fact.dto;
public class FileInfo {
    private Long factureId;
    private String name;
    private String url;
    private String designation;
    private boolean isValide;

    public FileInfo(Long factureId, String name, String url, String designation, boolean isValide) {
        this.name = name;
        this.factureId = factureId;
        this.url = url;
        this.designation = designation;
        this.isValide = isValide;
    }

    public Long getFactureId() {
        return factureId;
    }

    public void setFactureId(Long factureId) {
        this.factureId = factureId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public boolean isValide() {
        return isValide;
    }

    public void setValide(boolean valide) {
        isValide = valide;
    }
}