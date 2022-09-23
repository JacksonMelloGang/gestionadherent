package fr.nimamoums.gestadher.ui;

import fr.nimamoums.gestadher.adherent.Adherent;
import fr.nimamoums.gestadher.adherent.GestionAdherents;

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
    private JTextField textField4;
    private JTextField tF_mail;
    private JComboBox comboBox1;
    private JTextField textField10;
    private JLabel tF_tel;
    private JComboBox tF_armes;
    private JComboBox cBx_praticite;
    private JComboBox comboBox3;

    public AjouterAdherentUI() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
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
                hidePlaceholder(e, "jj/mm/aaaa");
            }

            @Override
            public void focusLost(FocusEvent e) {
                showPlaceholder(e, "jj/mm/aaaa");
            }
        });
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

    private void onOK() {
        // add your code here
        for(Component component : contentPane.getComponents()){
            if(component instanceof JTextField){
                ((JTextField) component).setText(null);
            }
        }

        String name = tF_last_name.getText();
        String firstName = tF_first_name.getText();

        String sexe = null;
        if(fCheckBox.isSelected()){
            sexe = "M";
        }else {
            sexe = "F";
        }
        boolean assure = assuréCheckBox.isSelected();

        String dateBirth = tF_date_birth.getText();
        String cityBirth = tF_city_birth.getText();

        String nationality = tf_Nationalite.getText();

        String address = tF_adress.getText();
        String cpCity = tF_cp_city.getText();

        String tel = tF_tel.getText();
        String mail = tF_mail.getText();

        String pratique = (String) cBx_praticite.getSelectedItem();
        String armes = tF_armes.getSelectedItem().toString();

        new Adherent(
                GestionAdherents.getAdherents().size(),
                name,
                firstName,
                sexe,
                nationality,
                LocalDate.of(Integer.parseInt(dateBirth.split("/")[2]), Integer.parseInt(dateBirth.split("/")[1]), Integer.parseInt(dateBirth.split("/")[0])),
                cityBirth,
                address,
                cpCity,
                tel,
                mail,
                pratique,
                0,
                false,
                false,
                false,
                "Gaucher"
                );


        //dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        AjouterAdherentUI dialog = new AjouterAdherentUI();
        dialog.setTitle("Ajouter un adhérent");
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
