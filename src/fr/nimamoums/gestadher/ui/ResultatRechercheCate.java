package fr.nimamoums.gestadher.ui;

import javax.swing.*;

public class ResultatRechercheCate {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JList lSt_cats;
    private JTextArea fTf_catmember;
    private JTextArea fTf_infocat;

    public ResultatRechercheCate(){
        JFrame frame = new JFrame("Titre");
        frame.setContentPane(panel1);
        frame.setSize(500, 500);
        frame.pack();
        frame.setVisible(true);
    }
}
