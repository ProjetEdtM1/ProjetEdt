package fr.utln.projet.vue;

import javax.swing.*;
import java.awt.*;

public class ModuleVUE extends Fenetre {

    public static JButton ajout = new JButton("Ajouter");
    public static JButton annuler = new JButton("Annuler");

    public static JTextField ajoutmodule = new JTextField();
    public static JTextField nbHeureCm = new JTextField();
    public static JTextField nbHeureTd = new JTextField();
    public static JTextField nbHeureTp = new JTextField();

    public static JLabel intituleModule = new JLabel("Intitulé Module ");
    public static JLabel heureCm = new JLabel("nombre d'heures total des cours magistraux");
    public static JLabel heureTd = new JLabel("nombre d'heures total des travaux dirigés");
    public static JLabel heureTp = new JLabel("nombre d'heures total des travaux pratiques");

    public ModuleVUE() {
        super();
        JPanel global = new JPanel();
        this.setTitle("Ajouter un module");

        ajoutmodule.setPreferredSize(new Dimension(100, 30));
        nbHeureCm.setPreferredSize(new Dimension(100, 30));
        nbHeureTd.setPreferredSize(new Dimension(100, 30));
        nbHeureTp.setPreferredSize(new Dimension(100, 30));

        global.setLayout(new GridBagLayout());
        GridBagConstraints contrainte = new GridBagConstraints();

        // placement des JLabel sur la colonne 1

        contrainte.gridx = 1;
        contrainte.gridy = 1;
        global.add(intituleModule, contrainte);

        contrainte.gridy = 2;
        global.add(heureCm, contrainte);

        contrainte.gridy = 3;
        global.add(heureTd, contrainte);

        contrainte.gridy = 4;
        global.add(heureTp, contrainte);


        // placement des JTExtField sur la colonne 2

        contrainte.gridx = 2;
        contrainte.gridy = 1;
        global.add(ajoutmodule, contrainte);

        contrainte.gridy = 2;
        global.add(nbHeureCm, contrainte);

        contrainte.gridy = 3;
        global.add(nbHeureTd, contrainte);

        contrainte.gridy = 4;
        global.add(nbHeureTp, contrainte);

        // Placement des JButton en bas

        contrainte.gridy = 5;
        global.add(annuler, contrainte);

        contrainte.gridx = 1;
        global.add(ajout, contrainte);



        getContentPane().add(global);

    }


    public static void main(String[] args) {
        new ModuleVUE();
    }
}
