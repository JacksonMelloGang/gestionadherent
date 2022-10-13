package fr.nimamoums.gestadherent.materiel;

import fr.nimamoums.gestadherent.materiel.interfaces.Louable;
import fr.nimamoums.gestadherent.materiel.interfaces.Protection;

public class MasqueS extends Protection implements Louable {

    @Override
    public String nom() {
        return "Masque S";
    }

    @Override
    public double prix_location() {
        return 20;
    }

    @Override
    public double prix_caution() {
        return 100;
    }
}
