package fr.nimamoums.gestadherent.ui;

import fr.nimamoums.gestadherent.adherent.Adherent;
import fr.nimamoums.gestadherent.materiel.*;
import fr.nimamoums.gestadherent.materiel.interfaces.Louable;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;

import static fr.nimamoums.gestadherent.materiel.Materiel.*;

public class MaterielLoue extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JSpinner sP_masqueFE;
    private JSpinner sP_kitDeb;
    private JSpinner sP_Armes;
    private JSpinner sP_masksabre;
    private JSpinner sP_vestefleuret;
    private JSpinner sP_vestesabre;

    private HashMap<Materiel, Integer> materielLoue = new HashMap<Materiel, Integer>();

    public MaterielLoue(Adherent adherent) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        // setup value for each spiners for each material
        sP_masqueFE.setValue(adherent.getMateriels_louee().get(MATERIEL_MASQUEFE) == null ? 0 : adherent.getMateriels_louee().get(MATERIEL_MASQUEFE));
        sP_kitDeb.setValue(adherent.getMateriels_louee().get(MATERIEL_KITDEB) == null ? 0 : adherent.getMateriels_louee().get(MATERIEL_KITDEB));
        sP_Armes.setValue(adherent.getMateriels_louee().get(MATERIEL_ARME) == null ? 0 : adherent.getMateriels_louee().get(MATERIEL_ARME));
        sP_masksabre.setValue(adherent.getMateriels_louee().get(MATERIEL_MASQUES) == null ? 0 : adherent.getMateriels_louee().get(MATERIEL_MASQUES));
        sP_vestefleuret.setValue(adherent.getMateriels_louee().get(MATERIEL_VESTEFE) == null ? 0 : adherent.getMateriels_louee().get(MATERIEL_VESTEFE));
        sP_vestesabre.setValue(adherent.getMateriels_louee().get(MATERIEL_VESTES) == null ? 0 : adherent.getMateriels_louee().get(MATERIEL_VESTES));

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
    }

    public MaterielLoue() {
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
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        /**
        MaterielLoue dialog = new MaterielLoue();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
        */
    }

    public HashMap<Materiel, Integer> getMateriel(){
        int masqueFE_int = (int) sP_masqueFE.getValue();
        int kitDeb_int = (int) sP_kitDeb.getValue();
        int armes_int = (int) sP_Armes.getValue();
        int masksabre_int = (int) sP_masksabre.getValue();
        int vestefleuret_int = (int) sP_vestefleuret.getValue();
        int vestesabre_int = (int) sP_vestesabre.getValue();

        materielLoue.put(MATERIEL_MASQUEFE, masqueFE_int);
        materielLoue.put(MATERIEL_KITDEB, kitDeb_int);
        materielLoue.put(MATERIEL_ARME, armes_int);
        materielLoue.put(MATERIEL_MASQUES, masksabre_int);
        materielLoue.put(MATERIEL_VESTEFE, vestefleuret_int);
        materielLoue.put(MATERIEL_VESTES, vestesabre_int);

        return this.materielLoue;
    }
}
