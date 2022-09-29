package fr.nimamoums.gestadher.user;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class User implements Serializable {
    private String nom;
    private String prenom;
    private String adresse;
    private String tel;
    private String mail;
    private String nationalite;
    private String genre;
    private String pays_ville_naissance;
    private String ville_code_postal;
    private String nom_naissance;
    private LocalDate date_naissance;



    public User(String nom, String prenom, String genre, String adresse, String tel, String mail, String nationalite, String pays_ville_naissance, String ville_code_postal, LocalDate date_naissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.mail = mail;
        this.nationalite = nationalite;
        this.genre = genre;
        this.pays_ville_naissance = pays_ville_naissance;
        this.ville_code_postal = ville_code_postal;
        this.nom_naissance = nom.split(" ").length > 1 ? nom.split(" ")[1] : nom;
        this.date_naissance = date_naissance;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPays_ville_naissance() {
        return pays_ville_naissance;
    }

    public void setPays_ville_naissance(String pays_ville_naissance) {
        this.pays_ville_naissance = pays_ville_naissance;
    }

    public String getCode_postal() {
        return ville_code_postal;
    }

    public void setVilleAndCode_postal(String ville_code_postal) {
        this.ville_code_postal = ville_code_postal;
    }

    public String getNom_naissance() {
        return nom_naissance;
    }

    public void setNom_naissance(String nom_naissance) {
        this.nom_naissance = nom_naissance;
    }

    public LocalDate getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(LocalDate date_naissance) {
        this.date_naissance = date_naissance;
    }

}
