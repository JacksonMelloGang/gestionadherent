package fr.nimamoums.gestadher.ui;

import java.util.ArrayList;
import java.util.List;

public class ResultatRecherche {

    public static List<Object> results = new ArrayList<>();

    public static List<Object> getResults() {
        return results;
    }

    public static void addResult(Object result) {
        results.add(result);
    }

    public static void clearResults() {
        results.clear();
    }
}
