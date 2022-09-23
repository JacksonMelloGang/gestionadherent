package fr.nimamoums.gestadher;

import fr.nimamoums.gestadher.adherent.Adherent;
import fr.nimamoums.gestadher.adherent.GestionAdherents;
import fr.nimamoums.gestadher.club.GestionClubs;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        GestionClubs.createclubxmlfile();

        GestionClubs.loadlistfromxmlfile();

        //GestionClubs.printlistofclubs();




        GestionAdherents.saveListintoFile();

        GestionAdherents.setAdherentList(null);
        GestionAdherents.loadListofAdherentFromFile();
        System.out.println(GestionAdherents.getAdherentList().size());

    }

}
