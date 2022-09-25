package fr.nimamoums.gestadher.club;

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
    }


    public int getClubId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClubNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getClubAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getClubContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getClubTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getClubMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getClubSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
