package fr.nimamoums.gestadher.materiel;

import fr.nimamoums.gestadher.materiel.interfaces.Louable;
import fr.nimamoums.gestadher.materiel.interfaces.Protection;

public class MasqueFE extends Protection implements Louable {

    @Override
    public String nom() {
        return "Masque FE";
    }

    @Override
    public double prix_location() {
        return 15;
    }

    @Override
    public double prix_caution() {
        return 80;
    }
}
