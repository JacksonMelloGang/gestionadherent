package fr.nimamoums.gestadher.adherents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GestionAdherents {

    private static List<Adherent> adherentList = new ArrayList<>();
    private static HashMap<Adherent, Integer> adherent_tarif = new HashMap<>();

    public static HashMap<Adherent, Integer> getAdherent_tarif() {
        return adherent_tarif;
    }

    public static void addAdherent(Adherent adherent, int tarif) {
        adherentList.add(adherent);
        adherent_tarif.put(adherent, tarif);
    }



    public static Adherent getAdherentByIndex(int Adherentid){
        Adherent adherent = null;
        int i = 0;
        while(i < adherentList.size() && adherent == null){
            if(adherentList.get(i).getAdherentId() == Adherentid){
                adherent = adherentList.get(i);
            }
            i++;
        }

        return adherent;
    }

    public static List<Adherent> getListofAdherentByFile(){

        return null;
    }

    public static void addAdherent(Adherent adherent){
        adherentList.add(adherent);
    }

    public static void removeAdherent(Adherent adherent){
        adherentList.remove(adherent);
    }

    public static List<Adherent> getAdherents() {
        return adherentList;
    }
}
