package fr.nimamoums.gestadherent.materiel;

import fr.nimamoums.gestadherent.materiel.interfaces.Louable;

public enum Materiel {

    MATERIEL_ARME(new Armes()),
    MATERIEL_VESTES(new VesteElectriqueSabre()),
    MATERIEL_VESTEFE(new VesteElectriqueFleuret()),
    MATERIEL_MASQUEFE(new MasqueFE()),
    MATERIEL_MASQUES(new MasqueS()),

    MATERIEL_KITDEB(new KitDebutant());





    private Louable louable;

    Materiel(Louable louable){
        this.louable = louable;
    }

}
