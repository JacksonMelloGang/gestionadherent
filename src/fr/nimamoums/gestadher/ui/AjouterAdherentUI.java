package fr.nimamoums.gestadher.ui;

import fr.nimamoums.gestadher.adherent.Adherent;
import fr.nimamoums.gestadher.adherent.GestionAdherents;
import fr.nimamoums.gestadher.club.Club;
import fr.nimamoums.gestadher.club.GestionClubs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

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
    private JCheckBox assuréCheckBox;
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

    public AjouterAdherentUI() {

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);


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
                if(mCheckBox.isSelected()){
                    fCheckBox.setSelected(false);
                }
            }
        });

        fCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fCheckBox.isSelected()){
                    mCheckBox.setSelected(false);
                }
            }
        });
        tF_date_birth.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                hidePlaceholder(e, "dd/mm/YYYY");
            }

            @Override
            public void focusLost(FocusEvent e) {
                showPlaceholder(e, "dd/mm/YYYY");
            }
        });
        contentPane.addComponentListener(new ComponentAdapter() {
        });
    }

    public static void main(String[] args) {
        AjouterAdherentUI dialog = new AjouterAdherentUI();
        for (Club club : GestionClubs.getClubList()) {
            dialog.cBx_club.addItem(club.getClubNom());
        }

        dialog.setTitle("Ajouter un adhérent");
        dialog.setBounds(100, 100, 800, 800);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

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

    public JCheckBox getAssuréCheckBox() {
        return assuréCheckBox;
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

    public JComboBox getcBx_club() {
        return cBx_club;
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void onOK() throws InterruptedException {
        // add your code here

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
        String club = "";
        double montant = 0;
        boolean isAssure = false;

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

        if (tF_tel.getText().isEmpty()) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez saisir le numéro de téléphone de l'adhérent");
            tF_tel.requestFocus();
            return;
        }

        if (tF_mail.getText().isEmpty()) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez saisir l'adresse mail de l'adhérent");
            tF_mail.requestFocus();
            return;
        }
        /**
         if(tF_locatedMatos.getText().isEmpty()){
         Toolkit.getDefaultToolkit().beep();
         JOptionPane.showMessageDialog(this, "Veuillez saisir le matériel possédé par l'adhérent");
         tF_locatedMatos.requestFocus();
         return;
         }
         **/

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
            JOptionPane.showMessageDialog(this, "Veuillez saisir le club de l'adhérent");
            cBx_club.requestFocus();
            return;
        }

        if (tF_montant.getText().isEmpty()) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Veuillez saisir le montant de l'adhésion de l'adhérent");
            tF_montant.requestFocus();
            return;
        }


        name = tF_last_name.getText();
        firstName = tF_first_name.getText();

        if (fCheckBox.isSelected()) {
            sexe = "M";
        } else {
            sexe = "F";
        }
        boolean assure = assuréCheckBox.isSelected();

        dateBirth = LocalDate.parse(tF_date_birth.getText());
        cityBirth = tF_city_birth.getText();

        nationality = tf_Nationalite.getText();

        address = tF_adress.getText();
        cpCity = tF_cp_city.getText();

        tel = tF_tel.getText();
        mail = tF_mail.getText();

        act_pratique = (String) cBx_pratique.getSelectedItem();
        armes = (String) cBx_armes.getSelectedItem();

        int birthdate_year = Integer.parseInt(String.valueOf(dateBirth.getYear()));
        int birthdate_month = Integer.parseInt(String.valueOf(dateBirth.getMonthValue()));
        int birthdate_day = Integer.parseInt(String.valueOf(dateBirth.getDayOfMonth()));

        montant = Integer.parseInt(tF_montant.getText());

        new Adherent(
                GestionAdherents.getAdherents().size(),
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
                LocalDate.now(),
                act_pratique,
                0,
                false,
                false,
                false,
                "Gaucher",
                montant
        );

        // Clean everything when the new adherent has been registered
        for (Component component : contentPane.getComponents()) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText(null);
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
        //dispose();
    }
}
