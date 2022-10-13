package fr.nimamoums.gestadherent.categorie;

import java.io.Serializable;
import java.time.Year;

public class Categorie implements Serializable {

    private int catId;

    private String nom;

    private String code;

    private Year annee_min;

    private Year annee_max;

    private double cotisation;

    public Categorie(int catid, String nom, String code, Year annee_min, Year annee_max) {

        this.catId = catid;
        this.nom = nom;
        this.code = code;
        this.annee_min = annee_min;
        this.annee_max = annee_max;

    }


    public int getCatID() {
        return catId;
    }

    public void setCatID(int catId) {
        this.catId = catId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Year getAnnee_min() {
        return annee_min;
    }

    public void setAnnee_min(Year annee_min) {
        this.annee_min = annee_min;
    }

    public Year getAnnee_max() {
        return annee_max;
    }

    public void setAnnee_max(Year annee_max) {
        this.annee_max = annee_max;
    }

}
