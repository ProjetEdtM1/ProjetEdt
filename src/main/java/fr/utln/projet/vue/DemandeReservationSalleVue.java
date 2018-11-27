package fr.utln.projet.vue;

import sun.misc.JavaLangAccess;

import javax.swing.*;
import java.awt.*;
import java.awt.image.renderable.ContextualRenderedImageFactory;

public class DemandeReservationSalleVue extends Fenetre{

    private JPanel ajoutPanel = new JPanel(new GridBagLayout());
    private JPanel suppressionPanel = new JPanel(new GridBagLayout());

    private JLabel labelNumSalle = new JLabel("Salle ");
    private JLabel labelDateReservation = new JLabel("Date ");
    private JLabel labelHeureDebResa = new JLabel("Heure Debut ");
    private JLabel labelHeureFinResa = new JLabel("Heure Fin ");

    private JTextField textNumSalle = new JTextField();
    private JTextField textDateReservation = new JTextField();
    private JTextField textHeureDebutReservation = new JTextField();
    private JTextField textHeureFinReservation = new JTextField();

    private JButton boutonAjout = new JButton("Valider ");
    private JButton boutonAnnuler = new JButton("Annuler ");


    public DemandeReservationSalleVue() {
        super();
        this.setTitle("Gestion de reservation de salle");

        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints contrainte = new GridBagConstraints();

        ajoutPanel.setBorder(BorderFactory.createTitledBorder("Demande de reservation"));

//      Placement des labels associes aux champs

        contrainte.gridx = 0;
        contrainte.gridy = 0;
        ajoutPanel.add(labelNumSalle, contrainte);

        contrainte.gridy = 1;
        ajoutPanel.add(labelDateReservation, contrainte);

        contrainte.gridy = 2;
        ajoutPanel.add(labelHeureDebResa, contrainte);

        contrainte.gridy = 3;
        ajoutPanel.add(labelHeureFinResa, contrainte);


//      Placement des champs de saisie de texte de demande d'ajout

        textNumSalle = new JTextField("", 10);
        textDateReservation = new JTextField("", 10);
        textHeureDebutReservation = new JTextField("", 10);
        textHeureFinReservation = new JTextField("", 10);

        contrainte.gridx = 1;
        contrainte.gridy = 0;
        ajoutPanel.add(textNumSalle, contrainte);

        contrainte.gridy = 1;
        ajoutPanel.add(textDateReservation, contrainte);

        contrainte.gridy = 2;
        ajoutPanel.add(textHeureDebutReservation, contrainte);

        contrainte.gridy = 3;
        ajoutPanel.add(textHeureFinReservation, contrainte);

//      Placement des boutons ajout et annuler

        contrainte.gridx = 0;
        contrainte.gridy = 4;
        ajoutPanel.add(boutonAjout, contrainte);

        contrainte.gridx = 1;
        contrainte.gridy = 4;
        ajoutPanel.add(boutonAnnuler, contrainte);


        contrainte.gridx = 0;
        contrainte.gridy = 0;
        getContentPane().add(ajoutPanel, contrainte);

        setVisible(true);
    }
}
