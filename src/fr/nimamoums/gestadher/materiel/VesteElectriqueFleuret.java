package fr.nimamoums.gestadher.materiel;

import fr.nimamoums.gestadher.materiel.interfaces.Louable;
import fr.nimamoums.gestadher.materiel.interfaces.Protection;

public class VesteElectriqueFleuret extends Protection implements Louable {

    @Override
    public String nom() {
        return "Veste Electrique Fleuret";
    }

    @Override
    public double prix_location() {
        return 20;
    }

    @Override
    public double prix_caution() {
        return 130;
    }
}
