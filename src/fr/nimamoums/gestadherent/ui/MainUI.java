package fr.nimamoums.gestadherent.ui;

import fr.nimamoums.gestadherent.fs.AdherentFile;
import fr.nimamoums.gestadherent.fs.ClubFile;
import fr.nimamoums.gestadherent.user.adherent.Adherent;
import fr.nimamoums.gestadherent.categorie.Categorie;
import fr.nimamoums.gestadherent.user.adherent.GestionAdherents;
import fr.nimamoums.gestadherent.categorie.GestionCategories;
import fr.nimamoums.gestadherent.club.Club;
import fr.nimamoums.gestadherent.club.GestionClubs;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class MainUI {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTextField tF_name;
    private JTextField tF_fstname;
    private JTextField tF_birthdate;
    private JTextField tF_citybirth;

    private JTextField tF_genre;
    private JTextField tF_adr;
    private JTextField tF_pc;
    private JTextField tF_nationality;
    private JTextField tF_phone;
    private JTextField tF_mail;
    private JCheckBox cB_fleuret;
    private JCheckBox cB_sabre;
    private JCheckBox cB_sword;
    private JCheckBox cB_loisir;
    private JCheckBox cB_competitive;
    private JTextField tF_montant;
    private JButton bTn_matosloc;
    private JList lSt_clubs;
    private JTextField tF_clubTel;
    private JTextField tF_clubMail;
    private JTextField tF_clubSite;
    private JComboBox cBx_listentities;
    private JButton bTn_del;
    private JButton bTn_edit;
    private JButton bTn_add;
    private JComboBox cBx_filter_searchby;
    private JTextField tF_filter_search;
    private JComboBox cBx_categorie;
    private JCheckBox cH_assure;
    private JPanel adhr_panel;
    private JLabel lBl_reduc;
    private JLabel lBl_dateinc;
    private JCheckBox cB_fsex;
    private JCheckBox cB_msex;
    private JTextField tF_dateinc;
    private JLabel lBl_entity_id;
    private JComboBox cBx_club;
    private JLabel lBl_entity_name;
    private JTextField tF_clubNom;
    private JTextField tF_clubAdresse;
    private JTextField tF_clubContact;
    private JList lSt_cats;
    private JTextArea fTf_catmember;
    private JTextArea fTf_infocat;

    private boolean editmode = false;

    public MainUI() throws IOException {
        JFrame frame = new JFrame("Gestionnaire des Adhérents");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 380);
        frame.setIconImage(ImageIO.read(new File("src\\fr\\nimamoums\\gestadherent\\ui\\icon.jpg")));
        //frame.pack();
        frame.setVisible(true);

        // adherent tab
        for (Categorie categorie : GestionCategories.getCategories()) {
            cBx_categorie.addItem(categorie.getNom() + " - " + categorie.getCode());
        }

        for(Club club : GestionClubs.getClubs()){
            cBx_club.addItem(club.getClubNom());
        }

        for(Adherent adherent : GestionAdherents.getAdherents()){
            cBx_listentities.addItem(adherent.getNom().toUpperCase() + " " + adherent.getPrenom());
        }



        // categorie tab
        DefaultListModel listModel = new DefaultListModel();
        for(Categorie categorie : GestionCategories.getCategories()){
            listModel.add(listModel.size(), categorie.getNom() + " - " + categorie.getCode());
        }
        lSt_cats.setModel(listModel);

        // Search criterias
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


        ////////////////////////////////////////////////////////////////////////////////
        // Event Listeners
        ////////////////////////////////////////////////////////////////////////////////
        tabbedPane1.addChangeListener(e -> {
            setFilterSearchBy(tabbedPane1.getSelectedIndex());
            setEntityList(tabbedPane1.getSelectedIndex());
            emptyFields();
        });
        bTn_del.addActionListener(e -> {
            onbTn_del();
        });

        // checkboxs
        cB_competitive.addActionListener(e -> {
            if (cB_competitive.isSelected()) {
                cB_loisir.setSelected(false);
            }
        });
        cB_loisir.addActionListener(e -> {
            if (cB_loisir.isSelected()) {
                cB_competitive.setSelected(false);
            }
        });
        cB_fleuret.addActionListener(e -> {
            if (cB_fleuret.isSelected()) {
                cB_sabre.setSelected(false);
                cB_sword.setSelected(false);
            }
        });
        cB_sabre.addActionListener(e -> {
            if (cB_sabre.isSelected()) {
                cB_fleuret.setSelected(false);
                cB_sword.setSelected(false);
            }
        });
        cB_sword.addActionListener(e -> {
            if (cB_sword.isSelected()) {
                cB_fleuret.setSelected(false);
                cB_sabre.setSelected(false);
            }
        });
        bTn_add.addActionListener(e -> {
            onbTn_add(tabbedPane1.getSelectedIndex());
        });
        tF_filter_search.addActionListener(e -> {
            onSearch(e);
        });
        bTn_edit.addActionListener(e -> {
            onbTn_Adhredit(e, tabbedPane1.getSelectedIndex());
        });
        cBx_listentities.addActionListener(e -> {
            oncBx_listentities(e);
        });

        cB_competitive.addActionListener(e -> {
            oncH_competitiveClick(e);
        });

        panel1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                forceUpdateEntityList(tabbedPane1.getSelectedIndex());
            }
        });
        panel1.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                forceUpdateEntityList(tabbedPane1.getSelectedIndex());
            }
        });
        bTn_matosloc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "En cours de développement !");
            }
        });
        lSt_cats.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                onlSt_cats(e);
            }
        });
    }

    private void onlSt_cats(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()){
            String[] cat = lSt_cats.getSelectedValue().toString().split(" - ");
            Categorie categorie = GestionCategories.getCategoryByName(cat[0]);

            StringBuilder catInfo = new StringBuilder();
            catInfo.append("Nom : " + categorie.getNom() + "\n");
            catInfo.append("Code : " + categorie.getCode() + "\n");
            catInfo.append("Age Min : " + categorie.getAnnee_min() + "\n");
            catInfo.append("Age Max : " + categorie.getAnnee_max() + "\n");
            fTf_infocat.setText(catInfo.toString());

            // 2nd part of categorie, where every member of a category is listed
            StringBuilder catMemberString = new StringBuilder();
            int i = 0;
            for(Adherent adherent : GestionAdherents.getAdherents()){
                // if adherent has categorie
                if(adherent.getCategorie() != null){
                    if(adherent.getCategorie().getNom().equals(categorie.getNom())){
                        i += 1;
                        catMemberString.append("Membre N°"+ i + " : " + adherent.getNom().toUpperCase()).append(" ").append(adherent.getPrenom() + "\n");
                    }
                }
            }
            fTf_catmember.setText(catMemberString.toString());
        }
    }

    private void emptyFields() {
        for(int i = 0; i < adhr_panel.getComponentCount(); i++){
            if(adhr_panel.getComponent(i) instanceof JTextField){
                ((JTextField) adhr_panel.getComponent(i)).setText("");
            } else {
                if(adhr_panel.getComponent(i) instanceof JCheckBox){
                    ((JCheckBox) adhr_panel.getComponent(i)).setSelected(false);
                }
            }
        }
    }

    private void oncH_competitiveClick(ActionEvent e) {
        if (cB_competitive.isSelected()) {
            cB_loisir.setSelected(false);
        }
    }

    private void setEntityList(int selectedIndex) {
        cBx_listentities.removeAllItems();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        switch(selectedIndex){
            case 0:
                lBl_entity_name.setText("Adhérents: ");

                if(GestionAdherents.getAdherents().isEmpty()){
                    cBx_listentities.addItem("Aucun Adhérent enregistré !");
                } else {
                    for(Adherent adherent : GestionAdherents.getAdherents()){
                        cBx_listentities.addItem(adherent.getNom().toUpperCase() + " " + adherent.getPrenom());
                    }
                }
                break;
            case 1:
                lBl_entity_name.setText("Clubs: ");
                if(GestionClubs.getClubs().isEmpty()){
                    cBx_listentities.addItem("Aucun Club enregistré !");
                } else {
                    for(Club club : GestionClubs.getClubs()){
                        cBx_listentities.addItem(club.getClubNom());
                    }
                }
                break;
            case 2:
                lBl_entity_name.setText("Catégories: ");
                if(GestionCategories.getCategories().isEmpty()){
                    cBx_listentities.addItem("Aucune Catégorie enregistré !");
                } else {
                    for(Categorie categorie : GestionCategories.getCategories()){
                        cBx_listentities.addItem(categorie.getNom());
                    }
                }
                break;
        }
    }

    private void oncBx_listentities(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        String entity = (String) cb.getSelectedItem();
        int selectedIndex = tabbedPane1.getSelectedIndex();

        switch(selectedIndex){
            case 0:
                // don't set any information in textbox if no entity is selected or if entity is null
                if(entity == null || entity.equals("")){
                    return;
                }

                Adherent adherent = GestionAdherents.getAdherentByName(entity);
                if(adherent == null){
                    return;
                }

                // set text of fields
                tF_name.setText(adherent.getNom());
                tF_fstname.setText(adherent.getPrenom());
                tF_birthdate.setText(String.valueOf(adherent.getDate_naissance()));
                tF_adr.setText(adherent.getAdresse());
                tF_mail.setText(adherent.getMail());
                tF_phone.setText(adherent.getTel());
                tF_montant.setText(String.valueOf(adherent.getMontant()));
                tF_dateinc.setText(String.valueOf(adherent.getDate_adhesion()));
                tF_citybirth.setText(adherent.getPays_ville_naissance());
                tF_nationality.setText(adherent.getNationalite());
                tF_pc.setText(adherent.getCode_postal());

                // set club only if it's not null
                if(adherent.getClub() != null){
                    Club adhrclub = GestionClubs.getClubById(adherent.getClub().getClubId());
                    cBx_club.setSelectedItem(adhrclub.getClubId());
                }

                // set categorie only if it's not null
                if(adherent.getCategorie() != null){
                    Categorie adhrcat = GestionCategories.getCategoryByCode(adherent.getCategorie().getCode());
                    cBx_categorie.setSelectedIndex(adhrcat.getCatID());
                }

                // tag id
                lBl_entity_id.setText(String.valueOf(adherent.getAdherentId()));

                // set selected category
                if(adherent.getGenre().toLowerCase().equalsIgnoreCase("f") || adherent.getGenre().toLowerCase().equalsIgnoreCase("femme")){
                    cB_fsex.setSelected(true);
                } else {
                    cB_msex.setSelected(true);
                }

                if(adherent.isAssured()){
                    cH_assure.setSelected(true);
                } else {
                    cH_assure.setSelected(false);
                }

                if(adherent.getCatPratique().toLowerCase().equalsIgnoreCase("loisir")){
                    cB_loisir.setSelected(true);
                } else {
                    cB_competitive.setSelected(true);
                }

                if(adherent.getArme().toLowerCase().equalsIgnoreCase("fleuret")){
                    cB_fleuret.setSelected(true);
                    cB_sabre.setSelected(false);
                    cB_sword.setSelected(false);
                } else if(adherent.getArme().toLowerCase().equalsIgnoreCase("sabre")){
                    cB_sabre.setSelected(true);
                    cB_fleuret.setSelected(false);
                    cB_sword.setSelected(false);
                } else {
                    cB_sword.setSelected(true);
                    cB_fleuret.setSelected(false);
                    cB_sabre.setSelected(false);
                }



                break;
            case 1:

                if(entity == null || entity.equals("")){
                    return;
                }

                Club club = GestionClubs.getClubByName(entity);
                lBl_entity_id.setText(String.valueOf(club.getClubId()));

                tF_clubNom.setText(club.getClubNom());
                tF_clubAdresse.setText(club.getClubAdresse());
                tF_clubContact.setText(club.getClubContact());
                tF_clubMail.setText(club.getClubMail());
                tF_clubTel.setText(club.getClubTel());
                tF_clubSite.setText(club.getClubSite());


                break;
        }
    }

    private void onbTn_Adhredit(ActionEvent e, int selectedIndex) {
        // enable edit mode only if edit mode not enabled
        if(editmode == false){
            editmode = true;
            JButton editbutton = (JButton) e.getSource();
            editbutton.setText("Enregistrer");

            // enable / set editable text fields
            JPanel selectedpanel = (JPanel) tabbedPane1.getComponent(tabbedPane1.getSelectedIndex());
            for(int i = 0; i < selectedpanel.getComponents().length; i++){
                if(adhr_panel.getComponents()[i] instanceof JTextField){
                    JTextField tF_textfield = (JTextField) adhr_panel.getComponents()[i];
                        tF_textfield.setEnabled(true);
                        tF_textfield.setEditable(true);
                } else {
                    JComponent jComponent = (JComponent) adhr_panel.getComponents()[i];
                    jComponent.setEnabled(true);
                }
            }



        } else {
            // editmode = true
            switch(selectedIndex){
                case 0:
                    if(verifyAdherentFields()){
                        int adherentId = Integer.parseInt(lBl_entity_id.getText());
                        String name = tF_name.getText();
                        String firstname = tF_fstname.getText();
                        String genre = "";
                        String birthdate = tF_birthdate.getText();
                        String address = tF_adr.getText();
                        String tel = tF_phone.getText();
                        String mail = tF_mail.getText();
                        Double montant = Double.parseDouble(tF_montant.getText());
                        Club club = GestionClubs.getClubByName(cBx_club.getSelectedItem().toString());
                        Categorie categorie = GestionCategories.getCategoryByCode(cBx_categorie.getSelectedItem().toString());
                        String pays_ville_naissance = tF_citybirth.getText();
                        String nationalite = tF_nationality.getText();
                        String arme = "";
                        String catprat = "";

                        // check if valid date, if not error and cancel update
                        try {
                            birthdate = String.valueOf(LocalDate.parse(birthdate));
                        } catch (DateTimeParseException ex){
                            JOptionPane.showMessageDialog(null, "La date de naissance n'est pas au bon format");
                            return;
                        }

                        if(cB_fleuret.isSelected()){
                            arme = "fleuret";
                        } else if(cB_sabre.isSelected()){
                            arme = "sabre";
                        } else {
                            arme = "epee";
                        }

                        if(cB_msex.isSelected()){
                            genre = "M";
                        } else {
                            genre = "F";
                        }

                        if(cB_loisir.isSelected()){
                            catprat = "loisir";
                        } else {
                            catprat = "compet";
                        }

                        Adherent adherent = GestionAdherents.getAdherentByIndex(adherentId);
                        if(adherent == null){
                            // adherent not found in list
                            JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de la récupération de l'adhérent.", "Erreur", JOptionPane.OK_OPTION);
                        }

                        adherent.setNom(name);
                        adherent.setPrenom(firstname);
                        adherent.setGenre(genre);
                        adherent.setDate_naissance(LocalDate.parse(birthdate));
                        adherent.setAdresse(address);
                        adherent.setTel(tel);
                        adherent.setMail(mail);
                        adherent.setMontant(montant);
                        adherent.setClub(club);
                        adherent.setCategorie(categorie);
                        adherent.setPays_ville_naissance(pays_ville_naissance);
                        adherent.setNationalite(nationalite);
                        adherent.setArme(arme);
                        adherent.setCatpratique(catprat);

                        JButton editbutton = (JButton) e.getSource();
                        editbutton.setText("Modifier");
                        editmode = false;
                        disable_components();

                        // update adherent
                        AdherentFile.saveFile();
                        forceUpdateEntityList(0);
                    }
                    break;
                case 1:

                    if(verifyClubFields()){
                        int clubId = Integer.parseInt(lBl_entity_id.getText());
                        String clubNom = tF_clubNom.getText();
                        String clubAdresse = tF_clubAdresse.getText();
                        String clubContact = tF_clubContact.getText();
                        String clubMail = tF_clubMail.getText();
                        String clubTel = tF_clubTel.getText();
                        String clubSite = tF_clubSite.getText();



                        Club club = GestionClubs.getClubById(clubId);
                        if(club == null){
                            return;
                        }

                        club.setClubNom(clubNom);
                        club.setClubAdresse(clubAdresse);
                        club.setClubContact(clubContact);
                        club.setClubMail(clubMail);
                        club.setClubTel(clubTel);
                        club.setClubSite(clubSite);

                        ClubFile.saveFile();

                        JButton editbutton = (JButton) e.getSource();
                        editbutton.setText("Modifier");
                        editmode = false;
                        disable_components();

                        ClubFile.saveFile();
                        forceUpdateEntityList(1);

                    }

                    break;
            }
        }
    }

    private void forceUpdateEntityList(int i) {
        cBx_listentities.removeAllItems();

        switch (i){
            case 0:
                // update adherent list
                for(Adherent adherent : GestionAdherents.getAdherents()){
                    cBx_listentities.addItem(adherent.getNom().toUpperCase() + " " + adherent.getPrenom());
                }
                break;
            case 1:
                // update club list
                for(Club club : GestionClubs.getClubs()){
                    cBx_listentities.addItem(club.getClubNom());
                }
                break;
            case 2:
                // update category list
                for(Categorie categorie : GestionCategories.getCategories()){
                    cBx_listentities.addItem(categorie.getNom() + " - " + categorie.getCode());
                }
                break;
        }
    }

    private boolean verifyAdherentFields() {
        if(tF_fstname.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Le champ prénom est vide");
            return false;
        }
        if(tF_name.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Le champ nom est vide");
            return false;
        }
        if(tF_birthdate.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Le champ date de naissance est vide");
            return false;
        }
        if(tF_adr.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Le champ adresse est vide");
            return false;
        }
        if(tF_phone.getText().isEmpty() || tF_phone.getText().length() != 10){
            JOptionPane.showMessageDialog(null, "Le champ téléphone est vide ou format invalide !");
            return false;
        }
        if(tF_mail.getText().isEmpty() && !tF_mail.getText().contains("@")){
            JOptionPane.showMessageDialog(null, "Le champ mail est vide ou email invalide !");
            return false;
        }
        if(tF_montant.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Le champ montant est vide");
            return false;
        }
        if(tF_dateinc.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Le champ date d'adhésion est vide");
            return false;
        }
        if(!cB_msex.isSelected() && !cB_fsex.isSelected()){
            JOptionPane.showMessageDialog(null, "Genre non séléctionné");
            return false;
        }

        if(!cB_competitive.isSelected() && !cB_loisir.isSelected()){
            JOptionPane.showMessageDialog(null, "Activité Pratique non séléctionné.");
            return false;
        }

        if(!cB_sabre.isSelected() && !cB_fleuret.isSelected() && !cB_sword.isSelected()){
            JOptionPane.showMessageDialog(null, "Arme non séléctionné.");
            return false;
        }

        return true;
    }

    private void onSearch(ActionEvent e) {
        ResultatRecherche.clearResults();

        String searchby = (String) cBx_filter_searchby.getSelectedItem();
        String search = tF_filter_search.getText();
        switch (tabbedPane1.getSelectedIndex()) {
            case 0:
                for(Adherent adherent : GestionAdherents.getAdherents()){
                    if(searchby.equals("Nom")){
                        if(adherent.getNom().toLowerCase().contains(search.toLowerCase())){
                            ResultatRecherche.addResult(adherent);
                        }
                    } else if(searchby.equals("Prénom")){
                        if(adherent.getPrenom().toLowerCase().contains(search.toLowerCase())){
                            ResultatRecherche.addResult(adherent);
                        }
                    } else if(searchby.equals("Club")){
                        if(adherent.getClub().getClubNom().toLowerCase().contains(search.toLowerCase())){
                            ResultatRecherche.addResult(adherent);
                        }
                    } else if(searchby.equals("Catégorie")){
                        if(adherent.getCategorie().getCode().toLowerCase().contains(search.toLowerCase())){
                            ResultatRecherche.addResult(adherent);
                        }
                    }
                }

                lSt_clubs.setListData(GestionAdherents.search(searchby, search).toArray());
                break;
            case 1:
                lSt_clubs.setListData(GestionClubs.search(searchby, search).toArray());
                break;
            case 2:
                lSt_clubs.setListData(GestionCategories.search(searchby, search).toArray());
                break;
        }
    }

    private void onbTn_add(int selectedIndex) {
        if(editmode == true){
            // cancel edit mode
            editmode = false;
            bTn_add.setText("Ajouter");
            disable_components();
        } else {
            switch(selectedIndex){
                case 0:
                    // cas: ajouter adherent
                    AjouterAdherentUI dialog = new AjouterAdherentUI();
                    for (Club club : GestionClubs.getClubs()) {
                        dialog.getcBx_club().addItem(club.getClubNom());
                    }

                    dialog.setTitle("Ajouter un adhérent");
                    dialog.setBounds(100, 100, 800, 800);
                    dialog.pack();
                    dialog.setVisible(true);
                    break;

                case 1:
                    // cas: ajouter club
                    if(verifyClubFields()){
                        String name = tF_clubNom.getText();
                        String adr = tF_clubAdresse.getText();
                        String contact = tF_clubContact.getText();
                        String tel = tF_clubTel.getText();
                        String mail = tF_clubMail.getText();
                        String site = tF_clubSite.getText();

                        Club club = new Club(GestionClubs.getClubs().size(), name, adr, contact, tel, mail, site);
                        GestionClubs.addClub(club);
                        ClubFile.saveFile();
                    }
                    break;
            }
        }
    }

    private boolean verifyClubFields() {
        if(tF_clubNom.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "Le champ nom du club est vide");
            return false;
        }

        if(tF_clubAdresse.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "Le champ adresse du club est vide");
            return false;
        }

        if(tF_clubTel.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "Le champ téléphone du club est vide");
            return false;
        }

        if(tF_clubMail.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "Le champ mail du club est vide");
            return false;
        }

        if(tF_clubSite.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "Le champ site du club est vide");
            return false;
        }

        return true;
    }

    private void disable_components() {
        for(int i = 0; i < adhr_panel.getComponents().length; i++){
            if(adhr_panel.getComponents()[i] instanceof JTextField){
                JTextField tF_textfield = (JTextField) adhr_panel.getComponents()[i];
                tF_textfield.setText("");
                tF_textfield.setEditable(false);
            } else {
                // Disable every components possible
                JComponent jComponent = (JComponent) adhr_panel.getComponents()[i];
                jComponent.setEnabled(false);

                // disable CheckBox
                cB_msex.setEnabled(false);
                cB_msex.setSelected(false);

                cB_fsex.setEnabled(false);
                cB_fsex.setSelected(false);

                cB_sabre.setEnabled(false);
                cB_sabre.setSelected(false);

                cB_fleuret.setEnabled(false);
                cB_fleuret.setSelected(false);

                cB_sword.setEnabled(false);
                cB_sword.setSelected(false);

                cH_assure.setEnabled(false);
                cH_assure.setSelected(false);

                cB_loisir.setSelected(false);
                cB_loisir.setEnabled(false);

                cB_competitive.setSelected(false);
                cB_competitive.setEnabled(false);

            }
        }
    }

    /////////////////////////////////////////////////////


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
            Adherent selectedadherent = GestionAdherents.getAdherentByIndex(cBx_listentities.getSelectedIndex());

            boolean success = GestionAdherents.removeAdherent(selectedadherent);
            if(success == true) {
                cBx_listentities.remove(cBx_listentities.getSelectedIndex());
                cBx_listentities.setSelectedIndex(0);
                JOptionPane.showMessageDialog(null, "Adherent supprimé avec succès", "Suppression", JOptionPane.INFORMATION_MESSAGE);
                emptyFields();
                forceUpdateEntityList(0);

                AdherentFile.saveFile();
            } else {
                JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de la suppression de l'adherent", "Suppression", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void debug_show_lists(){
        for(Adherent adh : GestionAdherents.getAdherents()){
            // print infos possible abt adherent
            System.out.println(adh.getAdherentId());
            System.out.println(adh.getNom());
            System.out.println(adh.getPrenom());
            System.out.println(adh.getGenre());
            System.out.println(adh.getNationalite());
            System.out.println(adh.getDate_naissance());
            System.out.println(adh.getPays_ville_naissance());
            System.out.println(adh.getAdresse());
            System.out.println(adh.getCode_postal());
            System.out.println(adh.getTel());
            System.out.println(adh.getMail());
            System.out.println(adh.getCatPratique());
            System.out.println(adh.isAssured());

        }
    }

}
