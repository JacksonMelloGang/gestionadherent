package fr.nimamoums.gestadherent.adherent;

import fr.nimamoums.gestadherent.club.Club;
import fr.nimamoums.gestadherent.gestion.GestionAdherents;
import fr.nimamoums.gestadherent.materiel.Materiel;
import fr.nimamoums.gestadherent.categorie.Categorie;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;

public class Adherent extends User implements Serializable {
    private Categorie categorie;
    private Club club;
    private int adherentId;

    private LocalDate date_adhesion;
    private String catpratique; // loisir ou compet

    private String arme;

    private String lateralite;

    private double cotisation;

    private boolean assured;

    private boolean reduction2emeadhere;

    private boolean reduction3andplusadhere;

    private double montant;

    private HashMap<Materiel, Integer> materiels_louee;

    //////////////////////////////////////////////////////////////////////////
    // CONSTRUCTEURS

    // normal
    public Adherent(String nom, String prenom, String genre, String nationalite, LocalDate date_naissance, String pays_ville_naissance, String adresse, String code_postal, String tel, String mail, String catpratique, boolean hasAssurance, boolean reduction2emeadhere, boolean reduction3andplusadhere, String lateralite, String arme, Club club, double montant, Categorie categorie) {
        super(nom, prenom, genre, adresse, tel, mail, nationalite, pays_ville_naissance, code_postal, date_naissance);

        this.adherentId = GestionAdherents.getAdherents().size();
        this.date_adhesion =  LocalDate.now();
        this.catpratique = catpratique;
        this.assured = hasAssurance;
        this.reduction2emeadhere = reduction2emeadhere;
        this.reduction3andplusadhere = reduction3andplusadhere;
        this.lateralite = lateralite;
        this.arme = arme;
        this.club = club;
        this.montant = montant;
        this.categorie = categorie;
        this.materiels_louee = new HashMap<Materiel, Integer>();
    }

    // without cateogire
    public Adherent(String nom, String prenom, String genre, String nationalite, LocalDate date_naissance, String pays_ville_naissance, String adresse, String code_postal, String tel, String mail, String catpratique, boolean hasAssurance, boolean reduction2emeadhere, boolean reduction3andplusadhere, String lateralite, String arme, Club club, double montant) {
        super(nom, prenom, genre, adresse, tel, mail, nationalite, pays_ville_naissance, code_postal, date_naissance);

        this.adherentId = GestionAdherents.getAdherents().size();
        this.date_adhesion =  LocalDate.now();
        this.catpratique = catpratique;
        this.assured = hasAssurance;
        this.reduction2emeadhere = reduction2emeadhere;
        this.reduction3andplusadhere = reduction3andplusadhere;
        this.lateralite = lateralite;
        this.arme = arme;
        this.club = club;
        this.montant = montant;
        this.categorie = null;
        this.materiels_louee = new HashMap<Materiel, Integer>();
    }


    // without club & categorie
    public Adherent(String nom, String prenom, String genre, String nationalite, LocalDate date_naissance, String pays_ville_naissance, String adresse, String code_postal, String tel, String mail, String catpratique, boolean hasAssurance, boolean reduction2emeadhere, boolean reduction3andplusadhere, String lateralite, double montant) {
        super(nom, prenom, genre, adresse, tel, mail, nationalite, pays_ville_naissance, code_postal, date_naissance);


        this.adherentId = GestionAdherents.getAdherents().size();
        this.date_adhesion =  LocalDate.now();
        this.catpratique = catpratique; // loisir ou compet
        this.assured = hasAssurance;
        this.reduction2emeadhere = reduction2emeadhere;
        this.reduction3andplusadhere = reduction3andplusadhere;
        this.lateralite = lateralite;
        this.arme = arme;
        this.montant = montant;
        this.materiels_louee = new HashMap<Materiel, Integer>();
    }

    // without club
    public Adherent(String nom, String prenom, String genre, String nationalite, LocalDate date_naissance, String pays_ville_naissance, String adresse, String code_postal, String tel, String mail, String catpratique, boolean hasAssurance, boolean reduction2emeadhere, boolean reduction3andplusadhere, String lateralite, String arme, double montant, Categorie categorie) {
        super(nom, prenom, genre, adresse, tel, mail, nationalite, pays_ville_naissance, code_postal, date_naissance);

        this.adherentId = GestionAdherents.getAdherents().size();
        this.date_adhesion =  LocalDate.now();
        this.catpratique = catpratique;
        this.assured = hasAssurance;
        this.reduction2emeadhere = reduction2emeadhere;
        this.reduction3andplusadhere = reduction3andplusadhere;
        this.lateralite = lateralite;
        this.arme = arme;
        this.montant = montant;
        this.categorie = categorie;
        this.materiels_louee = new HashMap<Materiel, Integer>();

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

    public String getCatPratique() {
        return catpratique;
    }

    public void setCatpratique(String catpratique) {
        this.catpratique = catpratique;
    }

    public String getLateralite() {
        return lateralite;
    }

    public void setLateralite(String lateralite) {
        this.lateralite = lateralite;
    }


    public int getAdherentId() {
        return adherentId;
    }

    public void setAdherentId(int adherentId) {
        this.adherentId = adherentId;
    }

    public double getCotisation() {
        return cotisation;
    }

    public void setCotisation(double cotisation) {
        this.cotisation = cotisation;
    }

    public void setAssured(boolean assured) {
        this.assured = assured;
    }

    public boolean isAssured() {
        return assured;
    }

    public boolean isReduction2emeadhere() {
        return reduction2emeadhere;
    }

    public boolean isReduction3andplusadhere() {
        return reduction3andplusadhere;
    }

    public Club getClub() {
        return club;
    }

    public HashMap<Materiel, Integer> getMateriels_louee() {
        return materiels_louee;
    }



    public void setClub(Club club) {
        this.club = club;
    }

    public void setReduction2emeadhere(boolean reduction2emeadhere) {
        this.reduction2emeadhere = reduction2emeadhere;
    }

    public void setReduction3andplusadhere(boolean reduction3andplusadhere) {
        this.reduction3andplusadhere = reduction3andplusadhere;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String getArme() {
        return arme;
    }

    public void setArme(String arme) {
        this.arme = arme;
    }

    public boolean addMaterialLouee(Materiel materiel, int qte){
        boolean ok = false;
        if(!materiels_louee.containsKey(materiel)){
            ok = true;
            materiels_louee.put(materiel, qte);
        }

        return ok;
    }

    public boolean removeMaterialLouee(Materiel materiel){
        boolean ok = false;
        if(materiels_louee.containsKey(materiel)){
            materiels_louee.replace(materiel, 0);
            ok = true;
        }

        return ok;
    }

}
