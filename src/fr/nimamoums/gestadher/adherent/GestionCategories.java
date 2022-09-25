package fr.nimamoums.gestadher.adherent;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
import java.util.ArrayList;
import java.util.List;

public class GestionCategories {

    private static final List<Categorie> categorieList = new ArrayList<>();

    public static Categorie getCategorieByCode(String Categorie_code){
        Categorie Categorie = null;
        int i = 0;
        while(i < categorieList.size() && Categorie == null){
            if(categorieList.get(i).getCode() == Categorie_code){
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



    public static boolean savelistintoxmlfile(){

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.parse(new FileInputStream("./data/categorie.xml"));

            Element root = document.getDocumentElement();

            for(Categorie categorie : categorieList){
                Element clubElement = document.createElement("categorie");
                clubElement.setAttribute("id", String.valueOf(categorie.getCatID()));

                Element clubNameElement = document.createElement("nom");
                clubNameElement.setTextContent(categorie.getNom());

                Element clubCodeElement = document.createElement("code");
                clubCodeElement.setTextContent(categorie.getCode());

                Element clubAnneeMinElement = document.createElement("annee_min");
                clubAnneeMinElement.setTextContent(categorie.getAnnee_min());

                Element clubAnneeMaxElement = document.createElement("annee_max");
                clubAnneeMaxElement.setTextContent(categorie.getAnnee_max());

                clubElement.appendChild(clubNameElement);
                clubElement.appendChild(clubCodeElement);
                clubElement.appendChild(clubAnneeMinElement);
                clubElement.appendChild(clubAnneeMaxElement);

                root.appendChild(clubElement);
            }

            DOMSource source = new DOMSource(document);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("indent", "yes");

            StreamResult result = new StreamResult("./data/categorie.xml");
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
     * Read club.xml file located  <strong>in the same directory as the jar file</strong> + /data
     **/
    public static int loadlistfromxmlfile(){
        // load xml file and convert it into list of categorie
        File file = new File("./data/categorie.xml");
        try {
            // check if /data folder exists, otherwise create it
            if(!file.getParentFile().exists()){
                return 4;
            }

            if(!file.exists()){
                System.out.println("File not found");
                return 1;
              }

            FileInputStream fileInputStream = new FileInputStream(file);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);

            // create xml reader
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fileInputStream);
            ////////////////////

            Element shelf = doc.getDocumentElement();
            NodeList xmllistofcat = shelf.getElementsByTagName("categories");


            for(int i = 0; i < xmllistofcat.getLength(); i++){
                Node node = xmllistofcat.item(i);

                Element cat_xml = (Element) node;

                int catid = Integer.parseInt(cat_xml.getAttribute("id"));
                String nom = cat_xml.getElementsByTagName("nom").item(0).getTextContent();
                String cat_code = cat_xml.getElementsByTagName("code").item(0).getTextContent();
                String annee_min = cat_xml.getElementsByTagName("annee_min").item(0).getTextContent();
                String annee_max = cat_xml.getElementsByTagName("annee_max").item(0).getTextContent();

                Categorie categorie_obj = new Categorie(catid, nom, cat_code, annee_min, annee_max);
                //Categorie club_obj = new Categorie(clubid, nom, adresse, contact, tel, mail, site);
            }


        } catch(Exception ex){
            return -1;
        }

        return 0;
    }

    public static boolean createxmlfile(){
        // create a categorie.xml file in data/ folder
        File file = new File("./data/categorie.xml");

        if(!file.getParentFile().exists()){
            System.out.println("Folder data/ not found, creating it");
            file.getParentFile().mkdirs();
        }

        if(!file.exists()){
            System.out.println("Creating categorie.xml");
            try {
                file.createNewFile();

                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

                Document document = documentBuilder.newDocument();
                Node node = document.createElement("categories");
                document.appendChild(node);

                //
                DOMSource source = new DOMSource(document);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                StreamResult result = new StreamResult(file);
                transformer.transform(source, result);

                return true;
            } catch (IOException | ParserConfigurationException | TransformerException e) {
                System.out.println("Error while creating categorie.xml:\n" + e.getMessage());
                throw new RuntimeException(e);
            }
        }

        return false;
    }

}
