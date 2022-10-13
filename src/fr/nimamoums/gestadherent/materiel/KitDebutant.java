package fr.nimamoums.gestadherent.materiel;

import fr.nimamoums.gestadherent.materiel.interfaces.Arme;
import fr.nimamoums.gestadherent.materiel.interfaces.Louable;

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
