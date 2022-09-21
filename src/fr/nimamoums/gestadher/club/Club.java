package fr.nimamoums.gestadher.club;

import fr.nimamoums.gestadher.adherent.GestionAdherents;

public class Club {

    private int id;
    private String nom;
    private String adresse;
    private String Contact;
    private String tel;
    private String mail;
    private String site;

    public Club(int id, String nom, String adresse, String contact, String tel, String mail, String site){
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.Contact = contact;
        this.tel = tel;
        this.mail = mail;
        this.site = site;

        GestionClubs.addClub(this);
    }


    public int getClubId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
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

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
