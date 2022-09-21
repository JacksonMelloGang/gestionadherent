package fr.nimamoums.gestadher.matos;

import fr.nimamoums.gestadher.matos.interfaces.Arme;
import fr.nimamoums.gestadher.matos.interfaces.Louable;

public class MasqueS extends Arme implements Louable {

    @Override
    public String nom() {
        return null;
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
