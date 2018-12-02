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

    private static JPanel ajoutcoursPanel = new JPanel(new GridBagLayout());

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

//        System.out.println("aaaaa " + professeurListModele.getElementAt(1).getClass());
        idProfesseurJcomboBox = new JComboBox<Professeur>(professeurListModele);
        idProfesseurJcomboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()) {
                    case ItemEvent.DESELECTED:
                        break;
                    case ItemEvent.SELECTED:

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
        dateCoursJTextField = new JTextField(coursControleur.getNouveauDateCours(),"",10);
        h_debutJTextField = new JTextField(coursControleur.getNouveauHDebutCours(),"",10);
        h_finJTextField = new JTextField(coursControleur.getNouveauHFinCours(),"",10);
        numSalleJTextField = new JTextField(coursControleur.getNouveauNumSalleCours(),"",10);

//        groupeEtudiantJcomboBox.getSelectedItem().toString();

        GridBagConstraints c = new GridBagConstraints();

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
        c.gridx = 1;
        c.gridy = 3;
        ajoutcoursPanel.add(dateCoursJTextField, c);
        c.gridx = 1;
        c.gridy = 4;
        ajoutcoursPanel.add(h_debutJTextField, c);
        c.gridx = 1;
        c.gridy = 5;
        ajoutcoursPanel.add(h_finJTextField, c);
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
