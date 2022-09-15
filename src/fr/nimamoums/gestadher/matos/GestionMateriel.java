package fr.nimamoums.gestadher.matos;

import java.util.ArrayList;
import java.util.List;

public class GestionMateriel {

    public static List<Veste> vesteList = new ArrayList<>();
    public static List<Arme> armeList = new ArrayList<>();
    public static List<Masque> masqueList = new ArrayList<>();

    public static void ajouterVeste(Veste veste) {
        vesteList.add(veste);
    }

    public static void ajouterArme(Arme arme) {
        armeList.add(arme);
    }

    public static void ajouterMasque(Masque masque) {
        masqueList.add(masque);
    }

    public static void supprimerVeste(Veste veste) {
        vesteList.remove(veste);
    }

    public static void supprimerArme(Arme arme) {
        armeList.remove(arme);
    }

    public static void supprimerMasque(Masque masque) {
        masqueList.remove(masque);
    }

    public static List<Veste> getVestes() {
        return vesteList;
    }

    public static List<Arme> getArmes() {
        return armeList;
    }

    public static List<Masque> getMasques() {
        return masqueList;
    }

}
