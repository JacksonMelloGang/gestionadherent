package fr.nimamoums.gestadherent.materiel;

import fr.nimamoums.gestadherent.materiel.interfaces.Arme;
import fr.nimamoums.gestadherent.materiel.interfaces.Masque;
import fr.nimamoums.gestadherent.materiel.interfaces.Protection;

import java.util.ArrayList;
import java.util.List;

public class GestionMateriel {

    public static List<Protection> vesteList = new ArrayList<>();
    public static List<Arme> armeList = new ArrayList<>();
    public static List<Masque> masqueList = new ArrayList<>();

    public static void ajouterVeste(Protection veste) {
        vesteList.add(veste);
    }

    public static void ajouterArme(Arme arme) {
        armeList.add(arme);
    }

    public static void ajouterMasque(Masque masque) {
        masqueList.add(masque);
    }

    public static void supprimerVeste(Protection veste) {
        vesteList.remove(veste);
    }

    public static void supprimerArme(Arme arme) {
        armeList.remove(arme);
    }

    public static void supprimerMasque(Masque masque) {
        masqueList.remove(masque);
    }

    public static List<Protection> getVestes() {
        return vesteList;
    }

    public static List<Arme> getArmes() {
        return armeList;
    }

    public static List<Masque> getMasques() {
        return masqueList;
    }

}
