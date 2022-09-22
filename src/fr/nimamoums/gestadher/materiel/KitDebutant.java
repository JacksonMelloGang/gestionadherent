package fr.nimamoums.gestadher.materiel;

import fr.nimamoums.gestadher.materiel.interfaces.Arme;
import fr.nimamoums.gestadher.materiel.interfaces.Louable;

public class KitDebutant extends Arme implements Louable {

    @Override
    public String nom() {
        return "Kit Debutant";
    }

    @Override
    public double prix_location() {
        return 30;
    }

    @Override
    public double prix_caution() {
        return 80;
    }

}
