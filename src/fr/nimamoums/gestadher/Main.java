package fr.nimamoums.gestadher;

import fr.nimamoums.gestadher.club.GestionClubs;
import fr.nimamoums.gestadher.exception.FolderNotFoundException;
import fr.nimamoums.gestadher.ui.MainUI;
import fr.nimamoums.gestadher.user.adherent.Adherent;
import fr.nimamoums.gestadher.user.adherent.GestionAdherents;
import fr.nimamoums.gestadher.user.adherent.categorie.GestionCategories;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        // create categories.xml into data/
        GestionCategories.createFile();

        // create club.xml into data/
        GestionClubs.createFile();

        // create adherents.bin into data/
        GestionAdherents.createFile();


        //////////////////////////////////
        // load files
        //////////////////////////////////

        // load categorie.xml
        GestionCategories.loadFile();

        // load club.xml
        GestionClubs.loadFile();

        // load adherents.bin
        try {
            GestionAdherents.loadListofAdherentFromFile();
        } catch (FolderNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Le dossier data n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Fichier adherents.bin introuvable Introuvable", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        System.out.println(GestionAdherents.getAdherents().size());

        Adherent adh = new Adherent(
                GestionAdherents.getAdherents().size(),
                "name",
                "firstName",
                "f",
                "nationality",
                LocalDate.of(2003, 11, 04),
                "cityBirth",
                "address",
                "cpCity",
                "tel",
                "mail",
                "loisir",
                true,
                true,
                false,
                "Gaucher",
                "Sabre",
                GestionClubs.getClubsByName("OMNISPORTS FROUARD/POMPEY"),
                300,
                GestionCategories.getCategoryByCode("M9")
        );

        GestionAdherents.addAdherent(adh);
        GestionAdherents.saveAdherents();


        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        try {
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }

        // create the main frame
        new MainUI();
    }

}
