package fr.utln.projet.vue;

import fr.utln.projet.controleur.ReserverSalleControleur;
import fr.utln.projet.modele.ReserverSalleModele;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ReserverSalleVue extends Fenetre{
    private ReserverSalleControleur reserverSalleControleur;
    private ReserverSalleModele reserverSalleModele;

//    private HeureDebResModel heureDebResModel;

    private JPanel ajoutPanel = new JPanel(new GridBagLayout());
    private JPanel heureDebResPanel = new JPanel(new GridBagLayout());
    private JPanel heureFinResPanel = new JPanel(new GridBagLayout());
    private JPanel dateResPanel = new JPanel(new GridBagLayout());
    private JPanel suppressionPanel = new JPanel(new GridBagLayout());

    private JLabel labelNumSalle = new JLabel("Salle ");
    private JLabel labelDateReservation = new JLabel("Date ");
    private JLabel labelHeureDebResa = new JLabel("Heure Debut ");
    private JLabel labelHeureFinResa = new JLabel("Heure Fin ");

    private JTextField textNumSalle = new JTextField();
    private JTextField textDateReservation = new JTextField();
    private JTextField textHeureDebutReservation = new JTextField();
    private JTextField textHeureFinReservation = new JTextField();

    private JComboBox jboxHeureDebRes = new JComboBox();
    private JComboBox jboxMinuteDebRes = new JComboBox();
    private JComboBox jboxHeureFinRes = new JComboBox();
    private JComboBox jboxMinuteFinRes = new JComboBox();
    private JComboBox jboxAnneeRes = new JComboBox();
    private JComboBox jboxMoisRes = new JComboBox();
    private JComboBox jboxJourRes = new JComboBox();

    private static JButton boutonAjout = new JButton("Valider ");
    private static JButton boutonAnnuler = new JButton("Annuler ");


    public ReserverSalleVue(ReserverSalleModele reserverSalleModele) {
        super();
        this.setTitle("Gestion de reservation de salle");

        this.reserverSalleModele = reserverSalleModele;
        this.reserverSalleControleur = new ReserverSalleControleur(this, reserverSalleModele);


        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints contrainte = new GridBagConstraints();

        ajoutPanel.setBorder(BorderFactory.createTitledBorder("Demande de reservation"));

        boutonAjout.setEnabled(false);

//        boutonAjout.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                reserverSalleControleur.ajouterReservationSalle();
//            }
//        });


//      Remplis un tableau d'heures possibles a selectionner dans la jcombobox
        ArrayList<Integer> heures = new ArrayList<>();
        for(int i = 8; i < 19; i++) {
            heures.add(i);
        }

//      Remplis la jcombobox de selection des heures de debut possible
        for (int s : heures) {
            jboxHeureDebRes.addItem(s);
        }

//      Remplis la jcombobox de selection des heures de fin possible
        for (int s : heures) {
            jboxHeureFinRes.addItem(s);
        }


//      Remplis un tableau des minutes possibles a selectionner dans la jcombobox
        ArrayList<Integer> minutes = new ArrayList<>();
        minutes.add(00);
        minutes.add(30);

//      remplissage de la jcombobox des minutes de debut de reservation
        for (int s: minutes) {
            jboxMinuteDebRes.addItem(s);
        }

//      remplissage de la jcombobox des minutes de fin de reservation
        for (int s: minutes) {
            jboxMinuteFinRes.addItem(s);
        }

//      remplis un tableau avec les jours possibles
        ArrayList<Integer> jours = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            jours.add(i);
        }

//      remplis la jcombobbox des jours de reservations avec les jours possibles
        for (int j: jours) {
            jboxJourRes.addItem(j);
        }

//      remplis un tableau avec les mois possibles
        ArrayList<String> mois = new ArrayList<>();
        mois.add("Janvier");
        mois.add("Fevrier");
        mois.add("Mars");
        mois.add("Avril");
        mois.add("Mai");
        mois.add("Juin");
        mois.add("Juillet");
        mois.add("Aout");
        mois.add("Septembre");
        mois.add("Octobre");
        mois.add("Novembre");
        mois.add("Decembre");

        for (String m: mois) {
            jboxMoisRes.addItem(m);
        }


        ArrayList<Integer> annee = new ArrayList<>();
        for (int i = 2018; i < 2040; i++) {
            annee.add(i);
        }

        for (int a: annee) {
            jboxAnneeRes.addItem(a);
        }


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


//      Placement des JCombobox du choix de la date dans la section ajout

        contrainte.gridx = 0;
        contrainte.gridy = 0;
        dateResPanel.add(jboxJourRes, contrainte);

        contrainte.gridx = 1;
        dateResPanel.add(jboxMoisRes, contrainte);

        contrainte.gridx = 2;
        dateResPanel.add(jboxAnneeRes, contrainte);

        contrainte.gridx = 1;
        contrainte.gridy = 1;
        ajoutPanel.add(dateResPanel, contrainte);

//      Placement des JCombobox du choix des heures de debut resevation dans la section ajout

        contrainte.gridx = 0;
        contrainte.gridy = 0;
        heureDebResPanel.add(jboxHeureDebRes, contrainte);

        contrainte.gridx = 1;
        heureDebResPanel.add(jboxMinuteDebRes, contrainte);

        contrainte.gridy = 2;
        ajoutPanel.add(heureDebResPanel, contrainte);

//      Placement des JCombobox du choix des heures de fin de reservation dans la secion ajout

        contrainte.gridx = 0;
        contrainte.gridy = 0;
        heureFinResPanel.add(jboxHeureFinRes, contrainte);

        contrainte.gridx = 1;
        heureFinResPanel.add(jboxMinuteFinRes, contrainte);

        contrainte.gridy = 3;
        ajoutPanel.add(heureFinResPanel, contrainte);


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

//        ajoutPanel.setBorder(BorderFactory.createTitledBorder("Annuler sa demande de reservation"));





        setVisible(true);
    }

    public static void setBoutonAjouter(boolean gris) {
        boutonAjout.setEnabled(gris);
    }
}
