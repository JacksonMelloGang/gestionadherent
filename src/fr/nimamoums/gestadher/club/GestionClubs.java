package fr.nimamoums.gestadher.club;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GestionClubs {

    private static List<Club> clubList = new ArrayList<>();

    public static void createFile(){

        File file = new File("./data/club.xml");
        boolean foldersuccess = true;
        boolean fileexists = false;

        // check if data/ folder exists, if not create it
        if (!file.getParentFile().exists()) {
            foldersuccess = file.getParentFile().mkdirs();
        }

        // check if club.xml exists, if not create it
        if(!file.exists() && foldersuccess){
            try {
                fileexists = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(fileexists){
            // add root info and node: 'clubs'
            try {
                // xml parser
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

                // create xml metadata and add <clubs/> node
                Document document = documentBuilder.newDocument();
                Element rootElement = document.createElement("clubs");
                document.appendChild(rootElement);

                // write xml file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource domSource = new DOMSource(document);
                StreamResult streamResult = new StreamResult(file);
                transformer.transform(domSource, streamResult);

            } catch (ParserConfigurationException | TransformerException e) {
                e.printStackTrace();
            }

        }
    }

    public static boolean saveFile(){

        // parse content of clubList into xml and save it into club.xml

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();
            Element rootElement = document.createElement("clubs"); // <clubs>

            for(Club club : getClubs()){
                Element xclub = document.createElement("club");
                xclub.setAttribute("id", String.valueOf(club.getClubId()));

                Element xnom = document.createElement("clubName");
                Element xcontact = document.createElement("contact");
                Element xadresse = document.createElement("adresse");
                Element xtelephone = document.createElement("tel");
                Element xemail = document.createElement("mail");
                Element xsite = document.createElement("site");

                xnom.setTextContent(club.getClubNom());
                xcontact.setTextContent(club.getClubContact());
                xadresse.setTextContent(club.getClubAdresse());
                xtelephone.setTextContent(club.getClubTel());
                xemail.setTextContent(club.getClubMail());
                xsite.setTextContent(club.getClubSite());

                xclub.appendChild(xnom);
                xclub.appendChild(xcontact);
                xclub.appendChild(xadresse);
                xclub.appendChild(xtelephone);
                xclub.appendChild(xemail);
                xclub.appendChild(xsite);

                rootElement.appendChild(xclub);
            }

            document.appendChild(rootElement);

            DOMSource source = new DOMSource(document);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("indent", "yes");

            StreamResult result = new StreamResult("./data/club.xml");
            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException e) {
            throw new RuntimeException(e);
        }


        return false;
    }

    public static boolean loadFile(){

        // parse into xml
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new FileInputStream("./data/club.xml"));

            Element root = document.getDocumentElement();
            NodeList clubs = root.getElementsByTagName("club");


            for(int i = 0; i < clubs.getLength(); i++){
                Node node = clubs.item(i);

                Element xclub = (Element) node;

                int club_id = Integer.parseInt(xclub.getAttribute("id"));
                String club_nom = xclub.getElementsByTagName("nom").item(0).getTextContent();
                String club_adresse = xclub.getElementsByTagName("adresse").item(0).getTextContent();
                String club_contact = xclub.getElementsByTagName("contact").item(0).getTextContent();
                String club_tel = xclub.getElementsByTagName("tel").item(0).getTextContent();
                String club_mail = xclub.getElementsByTagName("mail").item(0).getTextContent();
                String club_site = xclub.getElementsByTagName("site").item(0).getTextContent();

                Club club = new Club(club_id, club_nom, club_adresse, club_contact, club_tel, club_mail, club_site);
                GestionClubs.addClub(club);
            }

            return true;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors du chargement du fichier club.xml\nTrackback: " + e.getStackTrace().toString(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (IOException | SAXException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors du chargement du fichier club.xml\nTrackback: " + e.getStackTrace().toString(), "Erreur", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }


        return false;
    }


    public static List<Club> getClubs() {
        return clubList;
    }

    /**
     *
     * Add Club into clubList
     * Auto check if the id is not already used, if yes then set the id to the next available id
     * **/
    public static void addClub(Club club) {
        int latestclubid = 1;

        if(clubList.size() > 0){
            latestclubid = clubList.size()+1;
        }

        club.setClubId(latestclubid);
        clubList.add(club);
    }

    // remove club from clubList
    public static void removeClub(Club club) {
        clubList.remove(club);
    }

    // edit club from clubList
    public static void editClub(Club club) {
        for(Club c : clubList){
            if(c.getClubId() == club.getClubId()){
                c.setClubNom(club.getClubNom());
                c.setClubAdresse(club.getClubAdresse());
                c.setClubContact(club.getClubContact());
                c.setClubTel(club.getClubTel());
                c.setClubMail(club.getClubMail());
                c.setClubSite(club.getClubSite());
            }
        }
    }

    public static Collection<Object> search(String searchby, String search) {
        List<Club> foundochurence = new ArrayList<>();
        switch (searchby) {
            case "nom":
                for (Club club : clubList) {
                    if (club.getClubNom().toLowerCase().contains(search.toLowerCase())) {
                        foundochurence.add(club);
                    }
                }
                break;
            case "adresse":
                for (Club club : clubList) {
                    if (club.getClubAdresse().toLowerCase().contains(search.toLowerCase())) {
                        foundochurence.add(club);
                    }
                }
                break;
            case "contact":
                for (Club club : clubList) {
                    if (club.getClubContact().toLowerCase().contains(search.toLowerCase())) {
                        foundochurence.add(club);
                    }
                }
                break;
            case "tel":
                for (Club club : clubList) {
                    if (club.getClubTel().toLowerCase().contains(search.toLowerCase())) {
                        foundochurence.add(club);
                    }
                }
                break;
            case "mail":
                for (Club club : clubList) {
                    if (club.getClubMail().toLowerCase().contains(search.toLowerCase())) {
                        foundochurence.add(club);
                    }
                }
                break;
            case "site":
                for (Club club : clubList) {
                    if (club.getClubSite().toLowerCase().contains(search.toLowerCase())) {
                        foundochurence.add(club);
                    }
                }
                break;
        }


        return null;
    }

    public static Club getClubsByName(String clubname) {
        for(Club club : clubList){
            if(club.getClubNom().equals(clubname)){
                return club;
            }
        }
        return null;
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

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.parse(new FileInputStream("./data/club.xml"));

            Element root = document.getDocumentElement();

            for(Club club : clubList){
                Element clubElement = document.createElement("club");
                clubElement.setAttribute("id", String.valueOf(club.getClubId()));

                Element clubNameElement = document.createElement("clubName");
                clubNameElement.setTextContent(club.getClubNom());

                Element clubContactElement = document.createElement("contact");
                clubContactElement.setTextContent(club.getClubContact());

                Element clubAddressElement = document.createElement("adresse");
                clubAddressElement.setTextContent(club.getClubAdresse());

                Element clubPhoneElement = document.createElement("tel");
                clubPhoneElement.setTextContent(club.getClubAdresse());

                Element clubEmailElement = document.createElement("mail");
                clubEmailElement.setTextContent(club.getClubMail());

                Element clubSiteElement = document.createElement("site");
                clubSiteElement.setTextContent(club.getClubSite());

                clubElement.appendChild(clubNameElement);
                clubElement.appendChild(clubContactElement);
                clubElement.appendChild(clubAddressElement);
                clubElement.appendChild(clubPhoneElement);
                clubElement.appendChild(clubEmailElement);
                clubElement.appendChild(clubSiteElement);

                root.appendChild(clubElement);
            }

            DOMSource source = new DOMSource(document);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("indent", "yes");

            StreamResult result = new StreamResult("./data/club.xml");
            transformer.transform(source, result);

        } catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
            throw new RuntimeException(e);
        }

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
        File file = new File("./data/club.xml");
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

    public static boolean createxmlfile(){
        // create a club.xml file in data/ folder
        File file = new File("./data/club.xml");

        if(!file.getParentFile().exists()){
            System.out.println("Folder data/ not found, creating it");
            file.getParentFile().mkdirs();
        }

        if(!file.exists()){
            System.out.println("Creating club.xml");
            try {
                file.createNewFile();

                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

                Document document = documentBuilder.newDocument();
                Node node = document.createElement("clubs");
                document.appendChild(node);

                //
                DOMSource source = new DOMSource(document);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                StreamResult result = new StreamResult(file);
                transformer.transform(source, result);


                return true;
            } catch (IOException | ParserConfigurationException | TransformerException e) {
                System.out.println("Error while creating club.xml:\n" + e.getMessage());
                throw new RuntimeException(e);
            }
        }

        return false;
    }
}
