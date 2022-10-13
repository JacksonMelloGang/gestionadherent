package fr.nimamoums.gestadherent.club;

import java.io.Serializable;

public class Club implements Serializable {

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
    }


    public int getClubId() {
        return id;
    }

    public void setClubId(int id) {
        this.id = id;
    }

    public String getClubNom() {
        return nom;
    }

    public void setClubNom(String nom) {
        this.nom = nom;
    }

    public String getClubAdresse() {
        return adresse;
    }

    public void setClubAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getClubContact() {
        return Contact;
    }

    public void setClubContact(String contact) {
        Contact = contact;
    }

    public String getClubTel() {
        return tel;
    }

    public void setClubTel(String tel) {
        this.tel = tel;
    }

    public String getClubMail() {
        return mail;
    }

    public void setClubMail(String mail) {
        this.mail = mail;
    }

    public String getClubSite() {
        return site;
    }

    public void setClubSite(String site) {
        this.site = site;
    }
}
