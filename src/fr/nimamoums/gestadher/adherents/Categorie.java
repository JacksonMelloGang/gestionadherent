package fr.nimamoums.gestadher.adherents;

public class Categorie {

    private String catID;

    private String nom;

    private String code;

    private String annee_min;

    private String annee_max;



    public Categorie( String catID, String nom, String code, String annee_min, String annee_max){

        this.catID = catID;
        this.setNom(nom);
        this.setCode(code);
        this.setAnnee_min(annee_min);
        this.setAnnee_max(annee_max);
    }


    public String getCatID() {
        return catID;
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
