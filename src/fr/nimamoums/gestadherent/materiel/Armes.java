package fr.nimamoums.gestadherent.materiel;

import fr.nimamoums.gestadherent.materiel.interfaces.Arme;
import fr.nimamoums.gestadherent.materiel.interfaces.Louable;

public class Armes extends Arme implements Louable {

    @Override
    public String nom() {
        return "Armes";
    }

    @Override
    public double prix_location() {
        return 15;
    }

    @Override
    public double prix_caution() {
        return 0;
    }
}
