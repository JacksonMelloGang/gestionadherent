package fr.nimamoums.gestadher.adherents;

public class Adherent {

    private int adherentId;
    private String nom;
    private String nom_naissance;
    private String prenom;
    private String genre;
    private String nationalite;
    private String date_naissance;
    private String pays_ville_naissance;
    private String adresse;
    private String code_postal;
    private String tel;private String mail;
    private String date_adhesion;
    private String pratique;

    public Adherent(String nom, String prenom, String genre, String nationalite, String date_naissance, String pays_ville_naissance, String adresse, String code_postal, String tel, String mail) {
        this.nom = nom;
        this.nom_naissance = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.nationalite = nationalite;
        this.date_naissance = date_naissance;
        this.pays_ville_naissance = pays_ville_naissance;
        this.adresse = adresse;
        this.code_postal = code_postal;
        this.tel = tel;
        this.mail = mail;
    }

    public Adherent(String nom, String nom_naissance, String prenom, String genre, String nationalite, String date_naissance, String pays_ville_naissance, String adresse, String code_postal, String tel, String mail) {
        this.nom = nom;
        this.nom_naissance = nom_naissance;
        this.prenom = prenom;
        this.genre = genre;
        this.nationalite = nationalite;
        this.date_naissance = date_naissance;
        this.pays_ville_naissance = pays_ville_naissance;
        this.adresse = adresse;
        this.code_postal = code_postal;
        this.tel = tel;
        this.mail = mail;
    }


    public String getDate_adhesion() {
        return date_adhesion;
    }

    public void setDate_adhesion(String date_adhesion) {
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

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
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
}
