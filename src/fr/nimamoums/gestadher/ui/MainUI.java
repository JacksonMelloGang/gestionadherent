package fr.nimamoums.gestadher.ui;

import javax.swing.*;

public class MainUI {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JComboBox comboBox1;
    private JButton button1;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JCheckBox fleuretCheckBox;
    private JCheckBox sabreCheckBox;
    private JCheckBox epéeCheckBox;
    private JCheckBox loisirCheckBox;
    private JCheckBox compétitiveCheckBox;
    private JTextField textField12;
    private JButton afficherButton;

    public MainUI(){
        JFrame frame = new JFrame("MainUI");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MainUI();
    }
}
