package fr.nimamoums.gestadher;

import fr.nimamoums.gestadher.adherent.GestionAdherents;
import fr.nimamoums.gestadher.club.GestionClubs;

public class Main {

    public static void main(String[] args) {

        GestionClubs.createxmlfile();

        GestionClubs.loadlistfromxmlfile();

        //GestionClubs.printlistofclubs();





        GestionAdherents.setAdherentList(null);
        GestionAdherents.loadListofAdherentFromFile();
        System.out.println(GestionAdherents.getAdherentList().size());

    }

}
