package fr.nimamoums.gestadherent.fs;

import fr.nimamoums.gestadherent.club.Club;
import fr.nimamoums.gestadherent.gestion.GestionClubs;
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
import java.io.IOException;

public class ClubFile {
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

            for(Club club : GestionClubs.getClubs()){
                Element xclub = document.createElement("club");
                xclub.setAttribute("id", String.valueOf(club.getClubId()));

                Element xnom = document.createElement("nom");
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
}
