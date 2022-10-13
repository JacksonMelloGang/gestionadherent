package fr.nimamoums.gestadherent.materiel;

import fr.nimamoums.gestadherent.materiel.interfaces.Louable;
import fr.nimamoums.gestadherent.materiel.interfaces.Protection;

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
