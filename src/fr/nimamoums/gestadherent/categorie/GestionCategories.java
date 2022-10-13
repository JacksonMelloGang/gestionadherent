package fr.nimamoums.gestadherent.categorie;


import java.time.Year;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GestionCategories {

    private static final List<Categorie> categorieList = new ArrayList<>();

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

    public static Collection<Categorie> search(String searchby, String search) {
        Collection<Categorie> result = new ArrayList<>();
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
