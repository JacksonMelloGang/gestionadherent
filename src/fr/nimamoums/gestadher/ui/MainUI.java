package fr.nimamoums.gestadher.ui;

import fr.nimamoums.gestadher.adherent.Categorie;
import fr.nimamoums.gestadher.adherent.GestionAdherents;
import fr.nimamoums.gestadher.adherent.GestionCategories;
import fr.nimamoums.gestadher.club.Club;
import fr.nimamoums.gestadher.club.GestionClubs;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MainUI {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JCheckBox cH_fleuret;
    private JCheckBox cH_sabre;
    private JCheckBox cH_sword;
    private JCheckBox cH_loisir;
    private JCheckBox cH_competitive;
    private JTextField textField12;
    private JButton afficherButton;
    private JList list1;
    private JTextField textField11;
    private JTextField textField13;
    private JTextField textField14;
    private JTextField textField15;
    private JTextField textField16;
    private JTextField textField17;
    private JComboBox cBx_listentities;
    private JButton bTn_del;
    private JButton bTn_edit;
    private JButton bTn_add;
    private JComboBox cBx_filter_searchby;
    private JTextField tF_filter_search;
    private JComboBox cBx_categorie;
    private JCheckBox assuréCheckBox;
    private JPanel adhr_panel;
    private boolean editmode = false;

    public MainUI() {
        JFrame frame = new JFrame("MainUI");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        for (Categorie categorie : GestionCategories.getCategories()) {
            cBx_categorie.addItem(categorie.getNom());
        }

        ////////////////////////////////////////////////////////////////////////////////
        // Event Listeners
        ////////////////////////////////////////////////////////////////////////////////
        tabbedPane1.addChangeListener(e -> {
            setFilterSearchBy(tabbedPane1.getSelectedIndex());
        });
        bTn_del.addActionListener(e -> {
            onbTn_del();
        });

        // checkboxs
        cH_competitive.addActionListener(e -> {
            if (cH_competitive.isSelected()) {
                cH_loisir.setSelected(false);
            }
        });
        cH_loisir.addActionListener(e -> {
            if (cH_loisir.isSelected()) {
                cH_competitive.setSelected(false);
            }
        });
        cH_fleuret.addActionListener(e -> {
            if (cH_fleuret.isSelected()) {
                cH_sabre.setSelected(false);
                cH_sword.setSelected(false);
            }
        });
        cH_sabre.addActionListener(e -> {
            if (cH_sabre.isSelected()) {
                cH_fleuret.setSelected(false);
                cH_sword.setSelected(false);
            }
        });
        cH_sword.addActionListener(e -> {
            if (cH_sword.isSelected()) {
                cH_fleuret.setSelected(false);
                cH_sabre.setSelected(false);
            }
        });
        bTn_add.addActionListener(e -> {
            onbTn_add(tabbedPane1.getSelectedIndex());
        });
        tF_filter_search.addActionListener(e -> {
            onSearch(e);
        });
        bTn_edit.addActionListener(e -> {
            onbTn_Adhredit(e);
        });
    }

    private void onbTn_Adhredit(ActionEvent e) {
        if(editmode == false){
            editmode = true;

            JButton editbutton = (JButton) e.getSource();
            editbutton.setText("Sauvegarder");
            for(int i = 0; i < adhr_panel.getComponents().length; i++){
                if(adhr_panel.getComponents()[i] instanceof JTextField){
                    JTextField tF_textfield = (JTextField) adhr_panel.getComponents()[i];
                    tF_textfield.setEditable(true);
                } else {
                    JComponent jComponent = (JComponent) adhr_panel.getComponents()[i];
                    jComponent.setEnabled(true);
                }

            }
        } else {
            editmode = false;

            JButton editbutton = (JButton) e.getSource();
            editbutton.setText("Editer");
            for(int i = 0; i < adhr_panel.getComponents().length; i++){
                if(adhr_panel.getComponents()[i] instanceof JTextField){
                    JTextField tF_textfield = (JTextField) adhr_panel.getComponents()[i];
                    tF_textfield.setEditable(false);
                } else {
                    JComponent jComponent = (JComponent) adhr_panel.getComponents()[i];
                    jComponent.setEnabled(false);
                }
            }
        }
    }

    private void onSearch(ActionEvent e) {
        String searchby = (String) cBx_filter_searchby.getSelectedItem();
        String search = tF_filter_search.getText();
        switch (tabbedPane1.getSelectedIndex()) {
            case 0:
                list1.setListData(GestionAdherents.search(searchby, search).toArray());
                break;
            case 1:
                list1.setListData(GestionClubs.search(searchby, search).toArray());
                break;
            case 2:
                list1.setListData(GestionCategories.search(searchby, search).toArray());
                break;
        }
    }

    private void onbTn_add(int selectedIndex) {
        AjouterAdherentUI dialog = new AjouterAdherentUI();
        for (Club club : GestionClubs.getClubList()) {
            dialog.getcBx_club().addItem(club.getClubNom());
        }

        dialog.setTitle("Ajouter un adhérent");
        dialog.setBounds(100, 100, 800, 800);
        dialog.pack();
        dialog.setVisible(true);
    }

    public static void main(String[] args) {

        // create categories.xml into data/
        GestionCategories.createxmlfile();

        // create club.xml into data/
        GestionClubs.createFile();

        // create adherents.bin into data/
        GestionAdherents.saveAdherentsIntoFile();

        // load categorie.xml
        GestionCategories.loadlistfromxmlfile();

        // load club.xml
        GestionClubs.loadFile();

        System.out.println(GestionClubs.getClubList().size());

        // load adherents.bin
        GestionAdherents.loadListofAdherentFromFile();




        Club test = new Club(0, "Did", "7 Pompier", "Sus", "06577523", "aa@aaa.fr", "aa.fr");
        Club test2 = new Club(0, "Didier", "7 Pompier", "Sus", "06577523", "aa@aaa.fr", "aa.fr");

        GestionClubs.addClub(test);
        GestionClubs.addClub(test2);

        GestionClubs.saveFile();


        new MainUI();
    }

    private void setFilterSearchBy(int selectedIndex) {

        if (selectedIndex == 0) {
            // Adherents
            cBx_filter_searchby.removeAllItems();
            cBx_filter_searchby.addItem("Nom");
            cBx_filter_searchby.addItem("Prenom");
            cBx_filter_searchby.addItem("Sexe");
            cBx_filter_searchby.addItem("est Assurance");
            cBx_filter_searchby.addItem("Date de Naissance");
            cBx_filter_searchby.addItem("Ville de Naissance");
            cBx_filter_searchby.addItem("Adresse");
            cBx_filter_searchby.addItem("CodePostal");
            cBx_filter_searchby.addItem("Ville");
            cBx_filter_searchby.addItem("Telephone");
            cBx_filter_searchby.addItem("Email");
            cBx_filter_searchby.addItem("Club");
            cBx_filter_searchby.addItem("Categorie");
            cBx_filter_searchby.addItem("Arme");
            cBx_filter_searchby.addItem("Niveau");
            cBx_filter_searchby.addItem("DateInscription");
        } else {
            if (selectedIndex == 1) {
                // Clubs
                cBx_filter_searchby.removeAllItems();
                cBx_filter_searchby.addItem("Nom");
                cBx_filter_searchby.addItem("Adresse");
                cBx_filter_searchby.addItem("Contact");
                cBx_filter_searchby.addItem("Télephone");
                cBx_filter_searchby.addItem("Mail");
                cBx_filter_searchby.addItem("SiteWeb");
            } else {
                if (selectedIndex == 2) {
                    // Materiels
                    cBx_filter_searchby.removeAllItems();
                    cBx_filter_searchby.addItem("");
                    cBx_filter_searchby.addItem("");
                    cBx_filter_searchby.addItem("");
                }
            }
        }


    }

    private void onbTn_del() {
        int result = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer cet adherent ?", "Suppression", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            if (GestionAdherents.removeAdherent(GestionAdherents.getAdherentByIndex(list1.getSelectedIndex()))) {
                JOptionPane.showMessageDialog(null, "Adherent supprimé avec succès", "Suppression", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de la suppression de l'adherent", "Suppression", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
