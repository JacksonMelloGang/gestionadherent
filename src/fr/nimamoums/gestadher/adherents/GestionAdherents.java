package fr.nimamoums.gestadher.adherents;

import java.util.ArrayList;
import java.util.List;

public class GestionAdherents {

    private static List<Adherent> adherentList = new ArrayList<>();

    public static void getAdherents(){

    }

    public static void addAdherent(Adherent adherent){
        adherentList.add(adherent);
    }

    public static void removeAdherent(Adherent adherent){
        adherentList.remove(adherent);
    }

}
