package fr.nimamoums.gestadher.adherent;

import fr.nimamoums.gestadher.exception.FolderNotFoundException;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GestionAdherents implements Serializable {

    private static transient List<Adherent> adherentList = new ArrayList<>();

    public static void setAdherentList(List<Adherent> adherentList) {
        GestionAdherents.adherentList = adherentList;
    }

    public static void addAdherent(Adherent adherent, int tarif) {
        adherentList.add(adherent);
    }



    public static Adherent getAdherentByIndex(int Adherentid){
        Adherent adherent = null;
        int i = 0;
        while (i < adherentList.size() && adherent == null) {
            if (adherentList.get(i).getAdherentId() == Adherentid) {
                adherent = adherentList.get(i);
            }
            i++;
        }

        return adherent;
    }

    public static Adherent getAdherentByName(String name) {
        Adherent adherent = null;
        int i = 0;
        while (i < adherentList.size() && adherent == null) {
            if (adherentList.get(i).getNom().equalsIgnoreCase(name)) {
                adherent = adherentList.get(i);
            }
            i++;
        }

        return adherent;
    }

    public static Adherent getAdherentByFirstName(String name) {
        Adherent adherent = null;
        int i = 0;
        while (i < adherentList.size() && adherent == null) {
            if (adherentList.get(i).getPrenom().equalsIgnoreCase(name)) {
                adherent = adherentList.get(i);
            }
            i++;
        }

        return adherent;
    }

    public static int saveAdherentsIntoFile() {
        try {
            File file = new File("./data/adherents.bin");
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdir();
            }

            if(!file.exists()){
                file.createNewFile();
            }

            ObjectOutputStream objectInputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectInputStream.writeObject(adherentList);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }

        return 0;
    }


    public static boolean loadListofAdherentFromFile() throws FolderNotFoundException, FileNotFoundException {

        File file = new File("./data/adherents.bin");
        if(!file.getParentFile().exists()){
            throw new FolderNotFoundException("Folder not found");
        }

        if(!file.exists()){
            throw new FileNotFoundException("File not found");
        }

        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))){
            adherentList = (List<Adherent>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }



    public static void addAdherent(Adherent adherent) {
        adherentList.add(adherent);
    }

    public static boolean removeAdherent(Adherent adherent) {
        return adherentList.remove(adherent);
    }

    public static List<Adherent> getAdherents() {
        return adherentList;
    }

    public static boolean isAdherent(Adherent adherent){
        return adherentList.contains(adherent);
    }

    public static Collection<Adherent> search(String searchby, String criteria) {
        List<Adherent> foundochurence = new ArrayList<>();
        switch(searchby.toLowerCase()){
            case "nom":
                for(Adherent adhr : adherentList){
                    if(adhr.getNom().contains(criteria)){
                        foundochurence.add(adhr);
                    }
                }
                break;
            case "prenom":
                for(Adherent adhr : adherentList){
                    if(adhr.getPrenom().contains(criteria)){
                        foundochurence.add(adhr);
                    }
                }
                break;
            case "id":
                for(Adherent adhr : adherentList){
                    if(adhr.getAdherentId() == Integer.parseInt(criteria)){
                        foundochurence.add(adhr);
                    }
                }
                break;
            case "mail":
                for(Adherent adhr : adherentList){
                    if(adhr.getMail().contains(criteria)){
                        foundochurence.add(adhr);
                    }
                }
                break;
            case "tel":
                for(Adherent adhr : adherentList){
                    if(adhr.getTel().contains(criteria)){
                        foundochurence.add(adhr);
                    }
                }
                break;
            case "adresse":
                for(Adherent adhr : adherentList){
                    if(adhr.getAdresse().contains(criteria)){
                        foundochurence.add(adhr);
                    }
                }
                break;
            case "ville_naissance":
                for(Adherent adhr : adherentList){
                    if(adhr.getPays_ville_naissance().contains(criteria)){
                        foundochurence.add(adhr);
                    }
                }
                break;
            case "codepostal":
                for(Adherent adhr : adherentList){
                    if(adhr.getCode_postal().contains(criteria)){
                        foundochurence.add(adhr);
                    }
                }
                break;
            case "date_naissance":
                for(Adherent adhr : adherentList){
                    if(adhr.getDate_naissance().isEqual(LocalDate.parse(criteria))){
                        foundochurence.add(adhr);
                    }
                }
                break;
            case "sexe":
                for(Adherent adhr : adherentList){
                    if(adhr.getGenre().contains(criteria)){
                        foundochurence.add(adhr);
                    }
                }
                break;
            case "nationalite":
                for(Adherent adhr : adherentList){
                    if(adhr.getNationalite().contains(criteria)){
                        foundochurence.add(adhr);
                    }
                }
                break;
            case "assurance":
                for(Adherent adhr : adherentList){
                    if(adhr.isHasAssurance() == Boolean.parseBoolean(criteria)){
                        foundochurence.add(adhr);
                    }
                }
                break;
        }

        return null;
    }
}
