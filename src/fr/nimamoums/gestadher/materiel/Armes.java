package fr.nimamoums.gestadher.materiel;

import fr.nimamoums.gestadher.materiel.interfaces.Arme;
import fr.nimamoums.gestadher.materiel.interfaces.Louable;

public class Armes extends Arme implements Louable {

    @Override
    public String nom() {
        return "Armes";
    }

    @Override
    public double prix_location() {
        return 0;
    }

    @Override
    public double prix_caution() {
        return 0;
    }
}
