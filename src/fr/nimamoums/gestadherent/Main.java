package fr.nimamoums.gestadherent;

import fr.nimamoums.gestadherent.exception.FolderNotFoundException;
import fr.nimamoums.gestadherent.fs.AdherentFile;
import fr.nimamoums.gestadherent.fs.CategorieFile;
import fr.nimamoums.gestadherent.fs.ClubFile;
import fr.nimamoums.gestadherent.ui.MainUI;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        // create categories.xml into data/
        CategorieFile.createFile();

        // create club.xml into data/
        ClubFile.createFile();

        // create adherents.bin into data/
        AdherentFile.createFile();


        //////////////////////////////////
        // load files
        //////////////////////////////////

        // load categorie.xml
        CategorieFile.loadFile();

        // load club.xml
        ClubFile.loadFile();

        // load adherents.bin
        try {
            AdherentFile.loadFile();
        } catch (FolderNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Le dossier data n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Fichier adherents.bin introuvable Introuvable", "Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        // Windows Theme
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
