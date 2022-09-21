package fr.nimamoums.gestadher.club;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class GestionClubs {

    static List<Club> clubList = new ArrayList<>();

    public List<Club> getClubList() {
        return clubList;
    }

    public static void addClub(Club club) {
        int latestclubid = 1;

        for(Club listclub : clubList){
            if(listclub.getClubId() == club.getClubId()){
                latestclubid++;
            }
        }

        club.setId(latestclubid);
        clubList.add(club);
    }

    public void removeClub(Club club) {
        clubList.remove(club);
    }

    public Club getClubByIndex(int clubId){
        Club club = null;
        int i = 0;
        while(i < clubList.size() && club == null){
            if(clubList.get(i).getClubId() == clubId){
                club = clubList.get(i);
            }
            i++;
        }

        return club;
    }

    public static boolean savelistintoxmlfile(){

            // create a club.xml file and convert list into xml content and save it into file



            return false;
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
     public static int loadlistfromxmlfile(){
        // load xml file and convert it into list of club
        File file = new File("./data/clubs.xml");
        try {
            // check if /data folder exists, otherwise create it
            if(!file.getParentFile().exists()){
                //file.getParentFile().mkdirs();
                return 4;
                //System.out.println("Folder created");
            }

            if(!file.exists()){
                System.out.println("File not existing");
                return 1;
                // file not exits, so create file
                //file.createNewFile();
                //System.out.println("File created");

            }

            FileInputStream fileInputStream = new FileInputStream(file);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);

            // create xml reader
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fileInputStream);
            ////////////////////

            Element shelf = doc.getDocumentElement();
            NodeList xmllistofclub = shelf.getElementsByTagName("club");


            for(int i = 0; i < xmllistofclub.getLength(); i++){
                Node node = xmllistofclub.item(i);

                Element club_xml = (Element) node;
            
                int clubid = Integer.parseInt(club_xml.getAttribute("id"));
                String nom = club_xml.getElementsByTagName("nom").item(0).getTextContent();
                String adresse = club_xml.getElementsByTagName("adresse").item(0).getTextContent();
                String contact = club_xml.getElementsByTagName("contact").item(0).getTextContent();
                String tel = club_xml.getElementsByTagName("tel").item(0).getTextContent();
                String mail = club_xml.getElementsByTagName("mail").item(0).getTextContent();
                String site = club_xml.getElementsByTagName("site").item(0).getTextContent();

                Club club_obj = new Club(clubid, nom, adresse, contact, tel, mail, site);
            }


        } catch(Exception ex){
            return -1;
        }

        return 0;
    }

}
