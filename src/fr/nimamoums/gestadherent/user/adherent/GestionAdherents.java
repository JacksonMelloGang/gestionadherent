package fr.nimamoums.gestadherent.user.adherent;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GestionAdherents implements Serializable {

    private static transient List<Adherent> adherentList = new ArrayList<>();

    public static void setAdherentList(List<Adherent> adherentList) {
        GestionAdherents.adherentList = adherentList;
    }

    public static void addAdherent(Adherent adherent, int tarif) {
        adherentList.add(adherent);
    }



    public static Adherent getAdherentByIndex(int Adherentid){
        Adherent adherent = null;
        int i = 0;
        while (i < adherentList.size() && adherent == null) {
            if (adherentList.get(i).getAdherentId() == Adherentid) {
                adherent = adherentList.get(i);
            }
            i++;
        }

        return adherent;
    }

    public static Adherent getAdherentByName(String name) {
        Adherent adherent = null;
        int i = 0;
        while (i < adherentList.size() && adherent == null) {
            if(name.split(" ").length > 1){
                String nom = name.split(" ")[0];
                String sndname = name.split(" ")[1];
                if (adherentList.get(i).getNom().equalsIgnoreCase(nom) && adherentList.get(i).getPrenom().equalsIgnoreCase(sndname)) {
                    adherent = adherentList.get(i);
                }
            }
            i++;
        }

        return adherent;
    }

    public static Adherent getAdherentByFirstName(String name) {
        Adherent adherent = null;
        int i = 0;
        while (i < adherentList.size() && adherent == null) {
            if (adherentList.get(i).getPrenom().equalsIgnoreCase(name)) {
                adherent = adherentList.get(i);
            }
            i++;
        }

        return adherent;
    }


    public static void addAdherent(Adherent adherent) {
        adherentList.add(adherent);
    }

    public static boolean removeAdherent(Adherent adherent) {
        return adherentList.remove(adherent);
    }

    public static List<Adherent> getAdherents() {
        return adherentList;
    }

    public static boolean isAdherent(Adherent adherent){
        return adherentList.contains(adherent);
    }

    public static Collection<Adherent> search(String searchby, String criteria) {
        List<Adherent> foundochurence = new ArrayList<>();
        switch(searchby.toLowerCase()){
            case "nom":
                for(Adherent adhr : adherentList){
                    if(adhr.getNom().contains(criteria)){
                        foundochurence.add(adhr);
                    }
                }
                break;
            case "prenom":
                for(Adherent adhr : adherentList){
                    if(adhr.getPrenom().contains(criteria)){
                        foundochurence.add(adhr);
                    }
                }
                break;
            case "id":
                for(Adherent adhr : adherentList){
                    if(adhr.getAdherentId() == Integer.parseInt(criteria)){
                        foundochurence.add(adhr);
                    }
                }
                break;
            case "mail":
                for(Adherent adhr : adherentList){
                    if(adhr.getMail().contains(criteria)){
                        foundochurence.add(adhr);
                    }
                }
                break;
            case "tel":
                for(Adherent adhr : adherentList){
                    if(adhr.getTel().contains(criteria)){
                        foundochurence.add(adhr);
                    }
                }
                break;
            case "adresse":
                for(Adherent adhr : adherentList){
                    if(adhr.getAdresse().contains(criteria)){
                        foundochurence.add(adhr);
                    }
                }
                break;
            case "ville_naissance":
                for(Adherent adhr : adherentList){
                    if(adhr.getPays_ville_naissance().contains(criteria)){
                        foundochurence.add(adhr);
                    }
                }
                break;
            case "codepostal":
                for(Adherent adhr : adherentList){
                    if(adhr.getCode_postal().contains(criteria)){
                        foundochurence.add(adhr);
                    }
                }
                break;
            case "date_naissance":
                for(Adherent adhr : adherentList){
                    if(adhr.getDate_naissance().isEqual(LocalDate.parse(criteria))){
                        foundochurence.add(adhr);
                    }
                }
                break;
            case "sexe":
                for(Adherent adhr : adherentList){
                    if(adhr.getGenre().contains(criteria)){
                        foundochurence.add(adhr);
                    }
                }
                break;
            case "nationalite":
                for(Adherent adhr : adherentList){
                    if(adhr.getNationalite().contains(criteria)){
                        foundochurence.add(adhr);
                    }
                }
                break;
            case "assurance":
                for(Adherent adhr : adherentList){
                    if(adhr.isAssured() == Boolean.parseBoolean(criteria)){
                        foundochurence.add(adhr);
                    }
                }
                break;
        }

        return null;
    }

}
