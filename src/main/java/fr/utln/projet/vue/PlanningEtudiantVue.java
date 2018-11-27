package fr.utln.projet.vue;

import fr.utln.projet.controleur.CoursControleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class PlanningEtudiantVue extends Fenetre {
    private CoursControleur coursControleur;
    private String intituleGroupe;

    private JLabel labelTitre;
    private JTable tablePlanning;

    public PlanningEtudiantVue(String intituleGroupe){
        super();
        setTitle("Planning");
        this.coursControleur = new CoursControleur(this);
        this.intituleGroupe = intituleGroupe;


        final Container contentPane = getContentPane();
        JPanel panelGeneral = new JPanel();

        labelTitre = new JLabel("Planning");

        String[] entetes = {"Heure","Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche"};
        Object[][] donnes={{"8h","","","","","","",""},{"9h","","","","","","",""},{"10h","","","","","","",""}};
        tablePlanning = new JTable(donnes,entetes);

       // panelGeneral.add(labelTitre,BorderLayout.NORTH);
        panelGeneral.add(tablePlanning.getTableHeader(),BorderLayout.NORTH);
        panelGeneral.add(tablePlanning,BorderLayout.CENTER);

        contentPane.add(panelGeneral);



    }
}
