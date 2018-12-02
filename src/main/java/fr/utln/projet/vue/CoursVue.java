package fr.utln.projet.vue;


/*
 * Nom de classe : CoursVue
 *
 * Description   : Partie visible de mon interface permet de voir les formulaire d'ajout d'un cours
 *
 * Version       : 1.0
 *
 * Date          : 30/11/2018
 *
 * Copyright     : CLAIN Cyril
 */


import fr.utln.projet.controleur.ControleurEtudiant;
import fr.utln.projet.controleur.CoursControleur;
import fr.utln.projet.controleur.ProfesseurControleur;
import fr.utln.projet.modele.CoursModele;
import fr.utln.projet.modele.GroupeListModele;
import fr.utln.projet.modele.ProfesseurListModele;
import fr.utln.projet.utilisateur.Professeur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CoursVue extends Fenetre {

    private final CoursModele coursModele;
    private final CoursControleur coursControleur;
    private final ControleurEtudiant controleurEtudiant;
    private final ProfesseurControleur professeurControleur;


    //bundle utilisé pour acceder aux .properties
    private ResourceBundle rbBouton = ResourceBundle.getBundle("textBouton");
    private ResourceBundle rbLabel = ResourceBundle.getBundle("textLabel");

    //utilisation de liste groupe et etudiante en jcombobox
    private static GroupeListModele groupeListModele;
    private static ProfesseurListModele professeurListModele;

    private final JComboBox<Professeur> idProfesseurJcomboBox;
    private final JComboBox<String> groupeEtudiantJcomboBox;



    private final JLabel groupetudlabel = new JLabel(rbLabel.getString("Intitule groupe")+" :");
    private final JLabel numProflabel = new JLabel(rbLabel.getString("Id professeur")+" :");
    private final JLabel nomModulelabel = new JLabel(rbLabel.getString("Intitule module")+" :");
    private final JLabel dateCourslabel = new JLabel(rbLabel.getString("Date cours")+" :");
    private final JLabel h_debutlabel = new JLabel(rbLabel.getString("Heure de debut")+" :");
    private final JLabel h_finlabel = new JLabel(rbLabel.getString("Heure de fin")+" :");
    private final JLabel numSallelabel = new JLabel(rbLabel.getString("Numero de salle")+" :");


    private String groupeCours = new String();
    private JTextField nomModuleJTextField = new JTextField();
    private JTextField dateCoursJTextField = new JTextField();
    private JTextField h_debutJTextField = new JTextField();
    private JTextField h_finJTextField = new JTextField();
    private JTextField numSalleJTextField = new JTextField();

    private JComboBox jboxHeureDebCours = new JComboBox();
    private JComboBox jboxMinuteDebCours = new JComboBox();
    private JComboBox jboxHeureFinCours = new JComboBox();
    private JComboBox jboxMinuteFinCours = new JComboBox();
    private JComboBox jboxAnneeCours = new JComboBox();
    private JComboBox jboxMoisCours = new JComboBox();
    private JComboBox jboxJourCours = new JComboBox();

    private static JPanel ajoutcoursPanel = new JPanel(new GridBagLayout());
    private static JPanel dateCoursPanel = new JPanel(new GridBagLayout());
    private static JPanel heureDebCoursPanel = new JPanel(new GridBagLayout());
    private static JPanel heureFinCoursPanel = new JPanel(new GridBagLayout());

    private final JButton ajouterCoursJBouton = new JButton(rbBouton.getString("Ajouter"));
    private final JButton cancelAjouterCoursJButton = new JButton( rbBouton.getString("Annuler"));


    public CoursVue(final CoursControleur coursControleur, ControleurEtudiant controleurEtudiant, ProfesseurControleur professeurControleur) {

        this.coursControleur = coursControleur;
        this.coursModele = new CoursModele(coursControleur);
        this.controleurEtudiant = controleurEtudiant;
        this.professeurControleur = professeurControleur;
        this.groupeListModele = new GroupeListModele(this.controleurEtudiant.getListGroupe());
        this.professeurListModele = new ProfesseurListModele(this.professeurControleur.getListProfesseur());


        groupeEtudiantJcomboBox = new JComboBox<String>(groupeListModele);
        // groupeEtudiantJcomboBox.setEnabled(false);
        groupeEtudiantJcomboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()) {
                    case ItemEvent.DESELECTED:
                        break;
                    case ItemEvent.SELECTED:
                        groupeCours = String.valueOf(groupeEtudiantJcomboBox.getSelectedItem());
                        coursControleur.setTest(groupeCours);
                        break;
                }

            }
        });

        idProfesseurJcomboBox = new JComboBox<Professeur>(professeurListModele);
        idProfesseurJcomboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()) {
                    case ItemEvent.DESELECTED:
                        break;
                    case ItemEvent.SELECTED:
                        Professeur tmp;
                        tmp = (Professeur) idProfesseurJcomboBox.getSelectedItem();
                        coursControleur.setNouveauIdProfesseurCours(tmp.getIdprofesseur());

//                        coursControleur.setNouveauProfesseurCours(String.valueOf(idProfesseurJcomboBox.getSelectedItem()));
                        break;
                }

            }
        });

        ajouterCoursJBouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coursControleur.ajoutCours();
            }
        });

        nomModuleJTextField = new JTextField(coursControleur.getNouveauIntituleModule(),"",10);
//        dateCoursJTextField = new JTextField(coursControleur.getNouveauDateCours(),"",10);
        h_debutJTextField = new JTextField(coursControleur.getNouveauHDebutCours(),"",10);
        h_finJTextField = new JTextField(coursControleur.getNouveauHFinCours(),"",10);
        numSalleJTextField = new JTextField(coursControleur.getNouveauNumSalleCours(),"",10);


//        remplissage des JBox pour les cours

        //      Remplis un tableau d'heures possibles a selectionner dans la jcombobox
        ArrayList<Integer> heures = new ArrayList<>();
        for(int i = 8; i < 19; i++) {
            heures.add(i);
        }

//      Remplis la jcombobox de selection des heures de debut possible
        for (int s : heures) {
            jboxHeureDebCours.addItem(s);
        }

//      Remplis la jcombobox de selection des heures de fin possible
        for (int s : heures) {
            jboxHeureFinCours.addItem(s);
        }


//      Remplis un tableau des minutes possibles a selectionner dans la jcombobox
        ArrayList<Integer> minutes = new ArrayList<>();
        minutes.add(00);
        minutes.add(30);

//      remplissage de la jcombobox des minutes de debut de reservation
        for (int s: minutes) {
            jboxMinuteDebCours.addItem(s);
        }

//      remplissage de la jcombobox des minutes de fin de reservation
        for (int s: minutes) {
            jboxMinuteFinCours.addItem(s);
        }

//      remplis un tableau avec les jours possibles
        ArrayList<Integer> jours = new ArrayList<>();
        for (int i = 1; i < 32; i++) {
            jours.add(i);
        }

//      remplis la jcombobbox des jours de reservations avec les jours possibles
        for (int j: jours) {
            jboxJourCours.addItem(j);
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
            jboxMoisCours.addItem(m);
        }


        ArrayList<Integer> annee = new ArrayList<>();
        for (int i = 2018; i < 2040; i++) {
            annee.add(i);
        }

        for (int a: annee) {
            jboxAnneeCours.addItem(a);
        }



        GridBagConstraints c = new GridBagConstraints();

//      placement des JCombobox de la date, de l'heures de debut et de fin du cours

        c.gridx = 0;
        c.gridy = 0;
        dateCoursPanel.add(jboxJourCours, c);

        c.gridx = 1;
        dateCoursPanel.add(jboxMoisCours, c);

        c.gridx = 2;
        dateCoursPanel.add(jboxAnneeCours, c);

        c.gridx = 1;
        c.gridy = 3;
        ajoutcoursPanel.add(dateCoursPanel, c);

        c.gridx = 0;
        c.gridy = 0;
        heureDebCoursPanel.add(jboxHeureDebCours, c);

        c.gridx = 1;
        heureDebCoursPanel.add(jboxMinuteDebCours, c);

        c.gridx = 1;
        c.gridy = 4;
        ajoutcoursPanel.add(heureDebCoursPanel, c);

        c.gridx = 0;
        c.gridy = 0;
        heureFinCoursPanel.add(jboxHeureFinCours, c);

        c.gridx = 1;
        heureFinCoursPanel.add(jboxMinuteFinCours, c);

        c.gridx = 1;
        c.gridy = 5;
        ajoutcoursPanel.add(heureFinCoursPanel, c);


        // placement label
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        ajoutcoursPanel.add(groupetudlabel, c);
        c.gridy = 1;
        ajoutcoursPanel.add(numProflabel, c);
        c.gridy = 2;
        ajoutcoursPanel.add(nomModulelabel, c);
        c.gridy = 3;
        ajoutcoursPanel.add(dateCourslabel, c);
        c.gridy = 4;
        ajoutcoursPanel.add(h_debutlabel, c);
        c.gridy = 5;
        ajoutcoursPanel.add(h_finlabel, c);
        c.gridy = 6;
        ajoutcoursPanel.add(numSallelabel, c);

        //placement item(combobox / jtexfield)

        c.gridx = 1;
        c.gridy = 0;
        ajoutcoursPanel.add(groupeEtudiantJcomboBox, c);
        c.gridx = 1;
        c.gridy = 1;
        ajoutcoursPanel.add(idProfesseurJcomboBox, c);
        c.gridx = 1;
        c.gridy = 2;
        ajoutcoursPanel.add(nomModuleJTextField, c);
//        c.gridx = 1;
//        c.gridy = 3;
//        ajoutcoursPanel.add(dateCoursJTextField, c);
//        c.gridx = 1;
//        c.gridy = 4;
//        ajoutcoursPanel.add(h_debutJTextField, c);
//        c.gridx = 1;
//        c.gridy = 5;
//        ajoutcoursPanel.add(h_finJTextField, c);
        c.gridx = 1;
        c.gridy = 6;
        ajoutcoursPanel.add(numSalleJTextField,c);

        // placement bouton
        c.gridx = 0;
        c.gridy = 7;
        ajoutcoursPanel.add(ajouterCoursJBouton,c);
        c.gridx = 1;
        c.gridy = 7;
        ajoutcoursPanel.add(cancelAjouterCoursJButton,c);

        getContentPane().setLayout(new GridBagLayout());


        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        getContentPane().add(ajoutcoursPanel, c);

        setVisible(true);


    }
}
