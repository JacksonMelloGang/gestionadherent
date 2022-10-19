package fr.nimamoums.gestadherent.fs;

import fr.nimamoums.gestadherent.exception.FolderNotFoundException;
import fr.nimamoums.gestadherent.adherent.Adherent;
import fr.nimamoums.gestadherent.gestion.GestionAdherents;

import javax.swing.*;
import java.io.*;
import java.util.List;

public class AdherentFile {
    public static void createFile() {
        File file = new File("./data/adherents.bin");
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdir();
        }

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error while creating file adherent.bin.\n " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    public static boolean saveFile() {
        createFile();
        try {
            ObjectOutputStream objectInputStream = new ObjectOutputStream(new FileOutputStream("./data/adherents.bin"));
            objectInputStream.writeObject(GestionAdherents.getAdherents());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static boolean loadFile() throws FolderNotFoundException, FileNotFoundException {

        File file = new File("./data/adherents.bin");
        if(!file.getParentFile().exists()){
            throw new FolderNotFoundException("Folder not found");
        }

        if(!file.exists()){
            throw new FileNotFoundException("File not found");
        }

        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))){
            GestionAdherents.setAdherentList((List<Adherent>) objectInputStream.readObject());
        } catch (EOFException e) {
            //ignore (empty file)
            return false;
        } catch(ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error while loading file adherent.bin.\n " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error while loading file adherent.bin.\n " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }

        return false;
    }
}
