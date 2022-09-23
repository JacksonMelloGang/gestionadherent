package fr.nimamoums.gestadher.adherent;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GestionAdherents {

    private static List<Adherent> adherentList = new ArrayList<>();

    public static void setAdherentList(List<Adherent> adherentList) {
        GestionAdherents.adherentList = adherentList;
    }

    public static void addAdherent(Adherent adherent, int tarif) {
        adherentList.add(adherent);
    }



    public static Adherent getAdherentByIndex(int Adherentid){
        Adherent adherent = null;
        int i = 0;
        while(i < adherentList.size() && adherent == null){
            if(adherentList.get(i).getAdherentId() == Adherentid){
                adherent = adherentList.get(i);
            }
            i++;
        }

        return adherent;
    }

    public static int saveAdherentIntoFile(){
        return 0;
    }

    /**
     * -1 = Unexpected Exception
     * 0 = success<br>
     * 1 = file not found<br>
     * 2 = file not xml<br>
     * 3 = file not valid<br>
     * 4 = folder /data not found
     *
     * Read clubs.xml file located  <strong>in the same directory as the jar file</strong> + /data
     **/
    public static int loadListofAdherentFromFile(){

        File file = new File("./data/adherents.bin");
        if(!file.getParentFile().exists()){
            return 4;
        }

        if(!file.exists()){
            return 1;
        }

        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))){
            adherentList = (List<Adherent>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return -1;
        }

        return 0;
    }

    public static int saveListintoFile(){

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

    public static void addAdherent(Adherent adherent){
        adherentList.add(adherent);

        saveListintoFile();
    }

    public static void removeAdherent(Adherent adherent){
        adherentList.remove(adherent);
    }

    public static List<Adherent> getAdherents() {
        return adherentList;
    }

    public static List<Adherent> getAdherentList() {
        return adherentList;
    }

    public static boolean isAdherent(Adherent adherent){
        return adherentList.contains(adherent);
    }
}
