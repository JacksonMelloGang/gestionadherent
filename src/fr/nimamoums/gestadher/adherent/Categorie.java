package fr.nimamoums.gestadher.adherent;

public class Categorie {

    private int catId;

    private String nom;

    private String code;

    private String annee_min;

    private String annee_max;

    private double cotisation;

    public Categorie(int catid, String nom, String code, String annee_min, String annee_max){

        this.catId = catid;
        this.nom = nom;
        this.code = code;
        this.annee_min = annee_min;
        this.annee_max = annee_max;

        GestionCategories.addCategorie(this);
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

    public String getAnnee_min() {
        return annee_min;
    }

    public void setAnnee_min(String annee_min) {
        this.annee_min = annee_min;
    }

    public String getAnnee_max() {
        return annee_max;
    }

    public void setAnnee_max(String annee_max) {
        this.annee_max = annee_max;
    }

}
