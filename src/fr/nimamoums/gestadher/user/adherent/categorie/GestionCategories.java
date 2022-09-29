package fr.nimamoums.gestadher.user.adherent.categorie;


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
import java.time.Year;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GestionCategories {

    private static final List<Categorie> categorieList = new ArrayList<>();

    public static void createFile(){

        File file = new File("./data/categorie.xml");
        boolean foldersuccess = true;
        boolean fileexists = false;

        // check if data/ folder exists, if not create it
        if (!file.getParentFile().exists()) {
            foldersuccess = file.getParentFile().mkdirs();
        }

        // check if categorie.xml exists, if not create it
        if(!file.exists() && foldersuccess){
            try {
                fileexists = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "An error occured while creating the file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if(fileexists){
            // add root info and node: 'categories'
            try {
                // xml parser
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

                // create xml metadata and add <categories/> node
                Document document = documentBuilder.newDocument();
                Element rootElement = document.createElement("categories");
                document.appendChild(rootElement);

                // write xml file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource domSource = new DOMSource(document);
                StreamResult streamResult = new StreamResult(file);
                transformer.transform(domSource, streamResult);

            } catch (ParserConfigurationException | TransformerException e) {
                JOptionPane.showMessageDialog(null, "An error occured while creating the file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }

        }
    }

    public static boolean saveFile(){

        // parse content of categorieList into xml and save it into categorie.xml

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();
            Element rootElement = document.createElement("categories"); // <categories>

            for(Categorie categorie : categorieList){
                Element xcategorie = document.createElement("categorie");
                xcategorie.setAttribute("id", String.valueOf(categorie.getCatID()));

                Element xnom = document.createElement("nom");
                Element xcode = document.createElement("code");
                Element xanneemin = document.createElement("annee_min");
                Element xanneemax = document.createElement("annee_max");

                xnom.setTextContent(categorie.getNom());
                xcode.setTextContent(categorie.getCode());
                xanneemin.setTextContent(String.valueOf(categorie.getAnnee_min().getValue()));
                xanneemax.setTextContent(String.valueOf(categorie.getAnnee_max().getValue()));

                xcategorie.appendChild(xnom);
                xcategorie.appendChild(xcode);
                xcategorie.appendChild(xanneemin);
                xcategorie.appendChild(xanneemax);

                rootElement.appendChild(xcategorie);
            }

            document.appendChild(rootElement);

            DOMSource source = new DOMSource(document);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("indent", "yes");

            StreamResult result = new StreamResult("./data/categorie.xml");
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
            Document document = documentBuilder.parse(new FileInputStream("./data/categorie.xml"));

            Element root = document.getDocumentElement();
            NodeList categories = root.getElementsByTagName("categorie");


            for(int i = 0; i < categories.getLength(); i++){
                Node node = categories.item(i);

                Element xcategorie = (Element) node;

                int categorie_id = Integer.parseInt(xcategorie.getAttribute("id"));
                String categorie_nom = xcategorie.getElementsByTagName("nom").item(0).getTextContent();
                String categorie_code = xcategorie.getElementsByTagName("code").item(0).getTextContent();
                String categorie_anneemin = xcategorie.getElementsByTagName("annee_min").item(0).getTextContent();
                String categorie_anneemax = xcategorie.getElementsByTagName("annee_max").item(0).getTextContent();

                Categorie categorie = new Categorie(categorie_id, categorie_nom, categorie_code, Year.parse(categorie_anneemin), Year.parse(categorie_anneemax));
                GestionCategories.addCategorie(categorie);
            }

            return true;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors du chargement du fichier categorie.xml\nTrackback: " + e.getStackTrace().toString(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (IOException | SAXException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors du chargement du fichier categorie.xml\nTrackback: " + e.getStackTrace().toString(), "Erreur", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }

        return false;
    }
    
    public static Categorie getCategoryByCode(String Categorie_code){
        Categorie Categorie = null;
        int i = 0;
        while(i < categorieList.size() && Categorie == null){
            if(categorieList.get(i).getCode().equalsIgnoreCase(Categorie_code)){
                Categorie = categorieList.get(i);
            }
            i++;
        }

        return Categorie;
    }

    public static void addCategorie(Categorie categorie){
        categorieList.add(categorie);
    }

    public static void removeCategorie(Categorie categorie){
        categorieList.remove(categorie);
    }

    public static List<Categorie> getCategories() {
        return categorieList;
    }

    public static Collection<Object> search(String searchby, String search) {
        Collection<Object> result = new ArrayList<>();
        switch(searchby){
            case "nom":
                for(Categorie categorie : categorieList){
                    if(categorie.getNom().contains(search)){
                        result.add(categorie);
                    }
                }
                break;
            case "code":
                for(Categorie categorie : categorieList){
                    if(categorie.getCode().contains(search)){
                        result.add(categorie);
                    }
                }
                break;
            case "anneemin":
                for(Categorie categorie : categorieList){
                    if(categorie.getAnnee_min().compareTo(Year.parse(search)) == 0){
                        result.add(categorie);
                    }
                }
                break;
            case "anneemax":
                for(Categorie categorie : categorieList){
                    if(categorie.getAnnee_max().compareTo(Year.parse(search)) == 0){
                        result.add(categorie);
                    }
                }
                break;
        }

        return null;
    }

    public static Categorie getCategoryByName(String s) {
        Categorie categorie = null;
        int i = 0;
        while(i < categorieList.size() && categorie == null){
            if(categorieList.get(i).getNom().equalsIgnoreCase(s)){
                categorie = categorieList.get(i);
            }
            i++;
        }

        return categorie;
    }
}
