package fr.nimamoums.gestadher.adherent;

import java.io.Serializable;
import java.time.LocalDate;

public class Adherent implements Serializable {

    private int adherentId;
    private String nom;
    private String nom_naissance;
    private String prenom;
    private String genre;
    private String nationalite;
    private LocalDate date_naissance;
    private String pays_ville_naissance;
    private String adresse;
    private String code_postal;
    private String tel;

    private String mail;
    private LocalDate date_adhesion;
    private String pratique;

    private double cotisation;

    private boolean hasAssurance;

    private boolean reduction2emeadhere;

    private boolean reduction3andplusadhere;

    private double montant;

    //////////////////////////////////////////////////////////////////////////

    public Adherent(int adherentId, String nom, String prenom, String genre, String nationalite, LocalDate date_naissance, String pays_ville_naissance, String adresse, String code_postal, String tel, String mail, LocalDate date_adhesion, String pratique, double cotisation, boolean hasAssurance, boolean reduction2emeadhere, boolean reduction3andplusadhere, String lateralite, double montant) {
        for(Adherent adherent : GestionAdherents.getAdherents()){
            if(adherent.getAdherentId() == adherentId){
                adherentId++;
            }
        }

        this.adherentId = adherentId;
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.nationalite = nationalite;
        this.date_naissance = date_naissance;
        this.pays_ville_naissance = pays_ville_naissance;
        this.adresse = adresse;
        this.code_postal = code_postal;
        this.tel = tel;
        this.mail = mail;
        this.date_adhesion =  LocalDate.now();
        this.pratique = pratique;
        this.cotisation = cotisation;
        this.hasAssurance = hasAssurance;
        this.reduction2emeadhere = reduction2emeadhere;
        this.reduction3andplusadhere = reduction3andplusadhere;
        this.lateralite = lateralite;
        this.montant = 0;

        GestionAdherents.addAdherent(this);
    }

    //////////////////////////////////////////////////////////////////////////


    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public LocalDate getDate_adhesion() {
        return date_adhesion;
    }

    public void setDate_adhesion(LocalDate date_adhesion) {
        this.date_adhesion = date_adhesion;
    }

    public String getPratique() {
        return pratique;
    }

    public void setPratique(String pratique) {
        this.pratique = pratique;
    }

    public String getLateralite() {
        return lateralite;
    }

    public void setLateralite(String lateralite) {
        this.lateralite = lateralite;
    }

    private String lateralite;


    public int getAdherentId() {
        return adherentId;
    }

    public void setAdherentId(int adherentId) {
        this.adherentId = adherentId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom_naissance() {
        return nom_naissance;
    }

    public void setNom_naissance(String nom_naissance) {
        this.nom_naissance = nom_naissance;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public LocalDate getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(LocalDate date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getPays_ville_naissance() {
        return pays_ville_naissance;
    }

    public void setPays_ville_naissance(String pays_ville_naissance) {
        this.pays_ville_naissance = pays_ville_naissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public double getCotisation() {
        return cotisation;
    }

    public void setCotisation(double cotisation) {
        this.cotisation = cotisation;
    }

    public void setHasAssurance(boolean hasAssurance) {
        this.hasAssurance = hasAssurance;
    }

    public boolean isHasAssurance() {
        return hasAssurance;
    }

    public boolean isReduction2emeadhere() {
        return reduction2emeadhere;
    }

    public boolean isReduction3andplusadhere() {
        return reduction3andplusadhere;
    }

}
