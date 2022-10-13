package fr.nimamoums.gestadherent.materiel;

import fr.nimamoums.gestadherent.materiel.interfaces.Louable;
import fr.nimamoums.gestadherent.materiel.interfaces.Protection;

public class VesteElectriqueSabre extends Protection implements Louable {

    @Override
    public String nom() {
        return "Veste Electrique Sabre";
    }

    @Override
    public double prix_location() {
        return 25;
    }

    @Override
    public double prix_caution() {
        return 230;
    }
}
