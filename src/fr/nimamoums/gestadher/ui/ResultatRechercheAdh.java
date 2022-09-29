package fr.nimamoums.gestadher.ui;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ResultatRechercheAdh {

    public static List<Object> results = new ArrayList<>();
    private JTabbedPane tabbedPane1;
    private JPanel adhr_panel;
    private JTextField tF_name;
    private JTextField tF_birthdate;
    private JTextField tF_adr;
    private JTextField tF_phone;
    private JCheckBox cH_fleuret;
    private JCheckBox cH_sabre;
    private JCheckBox cH_sword;
    private JCheckBox cH_loisir;
    private JButton bTn_matosloc;
    private JTextField tF_mail;
    private JTextField tF_pc;
    private JTextField tF_nationality;
    private JTextField tF_citybirth;
    private JTextField tF_fstname;
    private JCheckBox cH_competitive;
    private JCheckBox cH_assure;
    private JCheckBox cB_fsex;
    private JCheckBox cB_msex;
    private JComboBox cBx_categorie;
    private JTextField tF_montant;
    private JLabel lBl_reduc;
    private JTextField tF_dateinc;
    private JComboBox cBx_club;

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
