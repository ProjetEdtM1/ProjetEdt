package fr.utln.projet.vue;

import javax.swing.*;
import java.awt.*;

public class AuthentificationVue extends JFrame {

    private static final JLabel labelLogin = new JLabel("Login");
    private static final JLabel labelMdp = new JLabel("Mot de passe");
    private static final JTextField champsLogin = new JTextField("");
    private static final JTextField champsMdp = new JTextField("");
    private static final JButton boutonConnexion = new JButton("Connexion");

    public AuthentificationVue(){
        super("Authentification");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(300,200);
        Container contentPane = getContentPane();

        JPanel panelGeneral = new JPanel();
        panelGeneral.setLayout(new BoxLayout(panelGeneral,BoxLayout.Y_AXIS));

        panelGeneral.add(labelLogin);
        panelGeneral.add(new JLabel(""));
        panelGeneral.add(champsLogin);
        panelGeneral.add(labelMdp);
        panelGeneral.add(new JLabel(""));
        panelGeneral.add(champsMdp);

        panelGeneral.add(boutonConnexion);
        contentPane.add(panelGeneral);
        setVisible(true);

    }

    public static void main(String[] args) {
        AuthentificationVue authentificationVue = new AuthentificationVue();
    }
}
