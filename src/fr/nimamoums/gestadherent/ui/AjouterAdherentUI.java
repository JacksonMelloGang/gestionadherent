package fr.nimamoums.gestadherent.ui;

import fr.nimamoums.gestadherent.fs.AdherentFile;
import fr.nimamoums.gestadherent.adherent.Adherent;
import fr.nimamoums.gestadherent.categorie.Categorie;
import fr.nimamoums.gestadherent.gestion.GestionAdherents;
import fr.nimamoums.gestadherent.club.Club;
import fr.nimamoums.gestadherent.gestion.GestionClubs;
import fr.nimamoums.gestadherent.gestion.GestionCategories;
import fr.nimamoums.gestadherent.materiel.Materiel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

public class AjouterAdherentUI extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField tF_last_name;
    private JTextField tF_first_name;
    private JTextField tF_date_birth;
    private JTextField tF_city_birth;
    private JTextField tf_Nationalite;
    private JTextField tF_adress;
    private JCheckBox fCheckBox;
    private JCheckBox mCheckBox;
    private JCheckBox assureCheckBox;
    private JTextField tF_cp_city;
    private JTextField tF_tel;
    private JTextField tF_mail;
    private JComboBox cBx_locatedmatos;
    private JTextField tF_montant;
    private JLabel téléphoneLabel;
    private JComboBox cBx_armes;
    private JComboBox cBx_pratique;
    private JComboBox cBx_club;
    private JLabel lbl_status_adhr_add;
    private JCheckBox cH_sndmember;
    private JCheckBox cH_thmember;
    private JComboBox cBx_cat;
    private JButton ajouterButton;
    public static HashMap<Materiel, Integer> matosloue;

    public AjouterAdherentUI() {

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        for(Categorie categorie : GestionCategories.getCategories()){
            cBx_cat.addItem(categorie.getNom() + " - " + categorie.getCode());
        }

        ////////////////////////////////////
        // EVENT LISTENERS
        ////////////////////////////////////
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    onOK();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        mCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onmCheckBox_Selected(mCheckBox, fCheckBox);
            }
        });

        fCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onfCheckBox_Selected(fCheckBox, mCheckBox);
            }
        });
        tF_date_birth.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                hidePlaceholder(e, "YYYY-mm-dd");
            }

            @Override
            public void focusLost(FocusEvent e) {
                showPlaceholder(e, "YYYY-mm-dd");
            }
        });
        contentPane.addComponentListener(new ComponentAdapter() {
        });
        cH_thmember.addActionListener(e -> {
            oncH_thmember(cH_thmember, cH_sndmember);
        });
        cH_sndmember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                oncH_sndmember(cH_sndmember, cH_thmember);
            }
        });
        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MaterielLoue dialog = new MaterielLoue();
                dialog.pack();
                dialog.setVisible(true);

                matosloue = dialog.getMateriel();
            }
        });
    }

    private void onmCheckBox_Selected(JCheckBox mCheckBox, JCheckBox fCheckBox) {
        if(mCheckBox.isSelected()){
            fCheckBox.setSelected(false);
        }
    }

    private void onfCheckBox_Selected(JCheckBox fCheckBox, JCheckBox mCheckBox) {
        if (fCheckBox.isSelected()) {
            mCheckBox.setSelected(false);
        }
    }

    private void oncH_sndmember(JCheckBox cH_sndmember, JCheckBox cH_thmember) {
        if(cH_sndmember.isSelected()){
            cH_thmember.setSelected(false);
            cH_thmember.setEnabled(false);
        } else {
            cH_thmember.setEnabled(true);
        }
    }

    private void oncH_thmember(JCheckBox cH_thmember, JCheckBox cH_sndmember) {
        if(cH_thmember.isSelected()){
            cH_sndmember.setSelected(false);
            cH_sndmember.setEnabled(false);
        } else {
            cH_sndmember.setEnabled(true);
        }
    }


    private void showPlaceholder(FocusEvent e, String s) {
        JTextField textField = (JTextField) e.getSource();
        if(textField.getText().isEmpty()){
            textField.setText(s);
        }
    }

    private void hidePlaceholder(FocusEvent e, String s) {
        JTextField textField = (JTextField) e.getSource();
        if(textField.getText().equals(s)){
            textField.setText("");
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void onOK() throws InterruptedException {
        String name = "";
        String firstName = "";
        String sexe = "";

        LocalDate dateBirth = null;
        String cityBirth = "";
        String nationality = "";
        String address = "";
        String cpCity = "";
        String tel = "";
        String mail = "";
        String locatedMatos = "";
        String armes = "";
        String act_pratique = "";
        Club club = null;
        Categorie categorie = null;

        double montant = 0;
        boolean isAssure = false;
        boolean isThMember = false;
        boolean isSndMember = false;

        int birthdate_year;
        int birthdate_month;
        int birthdate_day;

        if (tF_last_name.getText().isEmpty()) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez saisir le nom de l'adhérent");
            tF_last_name.requestFocus();
            return;
        }

        if (tF_first_name.getText().isEmpty()) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez saisir le prénom de l'adhérent");
            tF_first_name.requestFocus();
            return;
        }

        if (!mCheckBox.isSelected() && !fCheckBox.isSelected()) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez saisir le sexe de l'adhérent");
            mCheckBox.requestFocus();
            return;
        }

        if (tF_date_birth.getText().isEmpty()) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez saisir la date de naissance de l'adhérent");
            tF_date_birth.requestFocus();
            return;
        }

        if (tF_city_birth.getText().isEmpty()) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez saisir la ville de naissance de l'adhérent");
            tF_city_birth.requestFocus();
            return;
        }

        if (tf_Nationalite.getText().isEmpty()) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez saisir la nationalité de l'adhérent");
            tf_Nationalite.requestFocus();
            return;
        }

        if (tF_adress.getText().isEmpty()) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez saisir l'adresse de l'adhérent");
            tF_adress.requestFocus();
            return;
        }

        if (tF_cp_city.getText().isEmpty()) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez saisir le code postal et la ville de l'adhérent");
            tF_cp_city.requestFocus();
            return;
        }

        if (tF_tel.getText().isEmpty() || !tF_tel.getText().matches("0[0-9]{9}")) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez saisir le numéro de téléphone de l'adhérent dans un format correcte");
            tF_tel.requestFocus();
            return;
        }

        if (tF_mail.getText().isEmpty() || !tF_mail.getText().matches("[a-zA-Z0-9._-]+@[a-z0-9._-]{2,}\\.[a-z]{2,4}")) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez saisir l'adresse mail de l'adhérent dans un format correcte");
            tF_mail.requestFocus();
            return;
        }

        if (cBx_armes.getSelectedItem().toString().isEmpty()) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez saisir les armes possédées par l'adhérent");
            cBx_armes.requestFocus();
            return;
        }

        if (cBx_pratique.getSelectedItem().toString().isEmpty()) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez saisir les activités pratiquées par l'adhérent");
            cBx_pratique.requestFocus();
            return;
        }

        if (cBx_club.getSelectedItem().toString().isEmpty()) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez séléctionner le club de l'adhérent");
            cBx_club.requestFocus();
            return;
        }

        name = tF_last_name.getText();
        firstName = tF_first_name.getText();

        if (fCheckBox.isSelected()) {
            sexe = "M";
        } else {
            sexe = "F";
        }

        // boolean
        isAssure = assureCheckBox.isSelected();
        isSndMember = cH_sndmember.isSelected();
        isThMember = cH_thmember.isSelected();

        // date
        try {
            dateBirth = LocalDate.parse(tF_date_birth.getText());
        } catch (DateTimeParseException e){
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez saisir la date de naissance de l'adhérent au format AAAA-MM-JJ");
            tF_date_birth.requestFocus();
            return;
        }

        cityBirth = tF_city_birth.getText();

        nationality = tf_Nationalite.getText();

        address = tF_adress.getText();
        cpCity = tF_cp_city.getText();

        tel = tF_tel.getText();
        mail = tF_mail.getText();

        act_pratique = (String) cBx_pratique.getSelectedItem();
        armes = (String) cBx_armes.getSelectedItem();

        try {
            birthdate_year = Integer.parseInt(String.valueOf(dateBirth.getYear()));
            birthdate_month = Integer.parseInt(String.valueOf(dateBirth.getMonthValue()));
            birthdate_day = Integer.parseInt(String.valueOf(dateBirth.getDayOfMonth()));
        } catch(NumberFormatException ex){
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez saisir la date de naissance de l'adhérent au format AAAA-MM-JJ");
            tF_date_birth.requestFocus();
            return;
        }

        club = GestionClubs.getClubsByName(cBx_club.getSelectedItem().toString());

        // calcul montant (si pas de montant renseigné, on prend le montant par défaut)
        try {
            montant = tF_montant.getText().isEmpty() ? 0 : Double.parseDouble(tF_montant.getText());
        } catch (NumberFormatException ex){
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez saisir un montant valide pour l'adhésion de l'adhérent");
            tF_montant.requestFocus();
            return;
        }

        // calcul date naissance montant & 2nd member or 3third member of family
        if(birthdate_year <= 2002){
            montant = montant + 255;
            // member family
            if(isSndMember){
                montant = montant - 33.75;
            } else if(isThMember){
                montant = montant - 45;
            }

        } else {
            if(birthdate_year <= 2011){
                montant = montant + 220;
                // member family
                if(isSndMember){
                    montant = montant - 28.50;
                } else if(isThMember){
                    montant = montant - 38;
                }
            } else {
                montant = montant + 190;
                // member family
                if(isSndMember){
                    montant = montant - 24;
                } else if(isThMember){
                    montant = montant - 32;
                }
            }
        }
        // calcul assure or not
        if(!isAssure){
            montant = montant - 0.21;
        } else {
            montant = montant + 1.49;
        }

        tF_montant.setText(String.valueOf(montant));

        int resultconfirm = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment ajouter cet adhérent ?\nMontant à payer: " + montant + "€");
        if(resultconfirm != JOptionPane.YES_OPTION){
            return;
        }

        //int adherentId,
        // String nom,
        // String prenom,
        // String genre,
        // String nationalite,
        // LocalDate date_naissance,
        // String pays_ville_naissance,
        // String adresse,
        // String code_postal,
        // String tel,
        // String mail,
        // String catpratique, (loisir ou compet)
        // boolean hasAssurance,
        // boolean reduction2emeadhere,
        // boolean reduction3andplusadhere,
        // String lateralite,
        // String arme utilisé,
        // Club club,
        // double montant
        Adherent adh = new Adherent(
                        name,
                        firstName,
                        sexe,
                        nationality,
                        LocalDate.of(birthdate_year, birthdate_month, birthdate_day),
                        cityBirth,
                        address,
                        cpCity,
                        tel,
                        mail,
                        act_pratique,
                        isAssure,
                        isSndMember,
                        isThMember,
                        "Gaucher",
                        armes,
                        club,
                        montant
                );

        GestionAdherents.addAdherent(adh);

        /**
        adh.addMaterialLouee(Materiel.MATERIEL_ARME, matosloue.get(Materiel.MATERIEL_ARME));
        adh.addMaterialLouee(Materiel.MATERIEL_KITDEB, matosloue.get(Materiel.MATERIEL_KITDEB));
        adh.addMaterialLouee(Materiel.MATERIEL_MASQUEFE, matosloue.get(Materiel.MATERIEL_MASQUEFE));
        adh.addMaterialLouee(Materiel.MATERIEL_MASQUES, matosloue.get(Materiel.MATERIEL_MASQUES));
        adh.addMaterialLouee(Materiel.MATERIEL_VESTES, matosloue.get(Materiel.MATERIEL_VESTES));
        adh.addMaterialLouee(Materiel.MATERIEL_MASQUEFE, matosloue.get(Materiel.MATERIEL_VESTEFE));
        */

        AdherentFile.saveFile();

        JOptionPane.showMessageDialog(this, "Adhérent ajouté !", "Ajout d'un adhérent", JOptionPane.INFORMATION_MESSAGE);

        // Clean everything when the new adherent has been registered
        for (Component component : contentPane.getComponents()) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText(null);
            } else {
                if(component instanceof JComboBox){
                    ((JComboBox) component).setSelectedIndex(0);
                } else {
                    if(component instanceof JCheckBox){
                        ((JCheckBox) component).setSelected(false);
                    }
                }
            }
        }

        // Reset the comboboxes
        cBx_armes.setSelectedIndex(0);
        cBx_pratique.setSelectedIndex(0);
        cBx_club.setSelectedIndex(0);

        lbl_status_adhr_add.setText("Adhérent ajouté avec succès");
        lbl_status_adhr_add.setForeground(Color.GREEN);
        Thread.sleep(1000);
        lbl_status_adhr_add.setText("");
    }


    ////////////////////////////////////
    // GETTERS
    ////////////////////////////////////
    @Override
    public JPanel getContentPane() {
        return contentPane;
    }

    public JButton getButtonOK() {
        return buttonOK;
    }

    public JButton getButtonCancel() {
        return buttonCancel;
    }

    public JTextField gettF_last_name() {
        return tF_last_name;
    }

    public JTextField gettF_first_name() {
        return tF_first_name;
    }

    public JTextField gettF_date_birth() {
        return tF_date_birth;
    }

    public JTextField gettF_city_birth() {
        return tF_city_birth;
    }

    public JTextField getTf_Nationalite() {
        return tf_Nationalite;
    }

    public JTextField gettF_adress() {
        return tF_adress;
    }

    public JCheckBox getfCheckBox() {
        return fCheckBox;
    }

    public JCheckBox getmCheckBox() {
        return mCheckBox;
    }

    public JCheckBox getAssureCheckBox() {
        return assureCheckBox;
    }

    public JTextField gettF_cp_city() {
        return tF_cp_city;
    }

    public JTextField gettF_tel() {
        return tF_tel;
    }

    public JTextField gettF_mail() {
        return tF_mail;
    }

    public JComboBox getcBx_locatedmatos() {
        return cBx_locatedmatos;
    }

    public JTextField gettF_montant() {
        return tF_montant;
    }

    public JLabel getTéléphoneLabel() {
        return téléphoneLabel;
    }

    public JComboBox getcBx_armes() {
        return cBx_armes;
    }

    public JComboBox getcBx_pratique() {
        return cBx_pratique;
    }

    public JComboBox getcBx_club() {
        return cBx_club;
    }

}
