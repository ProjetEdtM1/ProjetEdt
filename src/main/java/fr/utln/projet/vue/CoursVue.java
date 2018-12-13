package fr.utln.projet.vue;


/*
 * Nom de classe : CoursVue
 *
 * Description   : Partie visible de mon interface permet de voir les formulaire d'ajout d'un cours
 *
 * Version       : 2.0
 *
 * Date          : 10/12/2018
 *
 * Copyright     : CLAIN Cyril, NOCITO Marc
 */


import fr.utln.projet.controleur.GererCoursControleur;
import fr.utln.projet.modele.*;
import fr.utln.projet.module.Module;
import fr.utln.projet.utilisateur.Professeur;
import fr.utln.projet.utilisateur.Salle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Vector;

public class CoursVue extends Fenetre {

    private final MenuProfRefVue menuProfRefVue;
    private final CoursModele coursModele;
    private final GererCoursControleur gererCoursControleur;
    //private final ProfesseurControleur professeurControleur;


    //bundle utilisé pour acceder aux .properties
    private ResourceBundle rbBouton = ResourceBundle.getBundle("textBouton");
    private ResourceBundle rbLabel = ResourceBundle.getBundle("textLabel");

    //utilisation de liste groupe et etudiante en jcombobox
    private static GroupeListModele groupeListModele;
    private static ProfesseurListModele professeurListModele;
    private static SalleListModele salleListModele;
    private static ArrayList<String> moduleListeModele;


    private JLabel groupetudlabel = new JLabel(rbLabel.getString("Intitule groupe")+" :");
    private JLabel numProflabel = new JLabel(rbLabel.getString("Id professeur")+" :");
    private JLabel nomModulelabel = new JLabel(rbLabel.getString("Intitule module")+" :");
    private JLabel dateCourslabel = new JLabel(rbLabel.getString("Date cours")+" :");
    private JLabel h_debutlabel = new JLabel(rbLabel.getString("Heure de debut")+" :");
    private JLabel h_finlabel = new JLabel(rbLabel.getString("Heure de fin")+" :");
    private JLabel numSallelabel = new JLabel(rbLabel.getString("Numero de salle")+" :");


    private String groupeCours = new String();
//    private JTextField nomModuleJTextField = new JTextField();


    private JComboBox<Professeur> idProfesseurJcomboBox;
    private JComboBox<String> groupeEtudiantJcomboBox;
    private JComboBox<String> moduleJcomboBox;
    private JComboBox jboxHeureDebCours = new JComboBox();
    private JComboBox jboxMinuteDebCours = new JComboBox();
    private JComboBox jboxHeureFinCours = new JComboBox();
    private JComboBox jboxMinuteFinCours = new JComboBox();
    private JComboBox jboxAnneeCours = new JComboBox();
    private JComboBox jboxMoisCours = new JComboBox();
    private JComboBox jboxJourCours = new JComboBox();
    private JComboBox<Salle> salleJcombobox;

    private static JPanel ajoutcoursPanel = new JPanel(new GridBagLayout());
    private static JPanel dateCoursPanel = new JPanel(new GridBagLayout());
    private static JPanel heureDebCoursPanel = new JPanel(new GridBagLayout());
    private static JPanel heureFinCoursPanel = new JPanel(new GridBagLayout());

    private final JButton ajouterCoursJBouton = new JButton(rbBouton.getString("Ajouter"));
    private final JButton cancelAjouterCoursJButton = new JButton( rbBouton.getString("Annuler"));


    public CoursVue(CoursModele coursModele, final MenuProfRefVue menuProfRefVue) {

        this.coursModele = new CoursModele();
        this.gererCoursControleur = new GererCoursControleur(coursModele);
        this.menuProfRefVue = menuProfRefVue;
       // this.controleurEtudiant = controleurEtudiant;
       // this.professeurControleur = professeurControleur;
        this.groupeListModele = new GroupeListModele(this.gererCoursControleur.getListGroupe());
        this.professeurListModele = new ProfesseurListModele(this.gererCoursControleur.getListProfesseur());

        this.salleListModele = new SalleListModele(this.gererCoursControleur.getListSalle());

        System.out.println(gererCoursControleur.getListeModule().get(0).getClass());
        this.moduleListeModele = this.gererCoursControleur.getListeModule();


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
                        gererCoursControleur.setnouveauGroupeCours(groupeCours);
                        break;
                }

            }
        });


        salleJcombobox = new JComboBox<>(salleListModele);
        salleJcombobox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()) {
                    case ItemEvent.DESELECTED:
                        break;
                    case ItemEvent.SELECTED:
                        Salle tmp;
                        tmp = (Salle) salleJcombobox.getSelectedItem();
                        System.out.println("hello " + tmp.getNumerosalle());
                        gererCoursControleur.setNouvauSalleCours(tmp.getNumerosalle());
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
                        gererCoursControleur.setNouveauIdProfesseurCours(tmp.getIdprofesseur());
                        break;
                }

            }
        });

        moduleJcomboBox = new JComboBox<String>();
        for (String e: moduleListeModele) {
            moduleJcomboBox.addItem(e);
        }

        moduleJcomboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch(e.getStateChange()) {
                    case ItemEvent.DESELECTED:
                        break;
                    case ItemEvent.SELECTED:
                        Module tmp;
                        tmp = (Module) moduleJcomboBox.getSelectedItem();
                        gererCoursControleur.setNouveaModuleCours(tmp.getIntitule());
                        break;
                }

            }
        });

        ajouterCoursJBouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gererCoursControleur.ajoutCours();
            }
        });

//        nomModuleJTextField = new JTextField(gererCoursControleur.getNouveauIntituleModule(),"",10);


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
//        minutes.add(30);

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
        ArrayList<Integer> mois = new ArrayList<>();
        for (int i = 01; i < 13; i++) {
            mois.add(i);
        }


        for (int m: mois) {
            jboxMoisCours.addItem(m);
        }


        ArrayList<Integer> annee = new ArrayList<>();
        for (int i = 2018; i < 2040; i++) {
            annee.add(i);
        }

        for (int a: annee) {
            jboxAnneeCours.addItem(a);
        }


        jboxJourCours.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()){
                    case ItemEvent.DESELECTED:
                        break;
                    case ItemEvent.SELECTED:
                        int jourCours = Integer.valueOf((Integer) jboxJourCours.getSelectedItem());
                        gererCoursControleur.setNouveauJourCours(jourCours);
                }
            }
        });

        jboxMoisCours.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()) {
                    case ItemEvent.DESELECTED:
                        break;
                    case ItemEvent.SELECTED:
                        gererCoursControleur.setNouveauMoisCours(Integer.valueOf((Integer) jboxMoisCours.getSelectedItem()));
                }
            }
        });

        jboxAnneeCours.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()){
                    case ItemEvent.DESELECTED:
                        break;
                    case ItemEvent.SELECTED:
                        gererCoursControleur.setNouveauAnneeCours(Integer.valueOf((Integer) jboxAnneeCours.getSelectedItem()));
                }
            }
        });

        jboxHeureDebCours.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()) {
                    case ItemEvent.DESELECTED:
                        break;
                    case ItemEvent.SELECTED:
                        gererCoursControleur.setNouveauHeureDebCours(Integer.valueOf((Integer) jboxHeureDebCours.getSelectedItem()));
                }
            }
        });

        jboxMinuteDebCours.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()) {
                    case ItemEvent.DESELECTED:
                        break;
                    case ItemEvent.SELECTED:
                        gererCoursControleur.setNouveauMinuteDebCours(Integer.valueOf((Integer) jboxMinuteDebCours.getSelectedItem()));
                }
            }
        });

        jboxHeureFinCours.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()) {
                    case ItemEvent.DESELECTED:
                        break;
                    case ItemEvent.SELECTED:
                        gererCoursControleur.setNouveauHeureFinCours(Integer.valueOf((Integer) jboxHeureFinCours.getSelectedItem()));
                }
            }
        });

        jboxMinuteFinCours.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()) {
                    case ItemEvent.DESELECTED:
                        break;
                    case ItemEvent.SELECTED:
                        gererCoursControleur.setNouveauMinuteFinCours(Integer.valueOf((Integer) jboxMinuteFinCours.getSelectedItem()));
                }
            }
        });



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
        ajoutcoursPanel.add(moduleJcomboBox, c);
        c.gridx = 1;
        c.gridy = 6;
        ajoutcoursPanel.add(salleJcombobox,c);

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

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                menuProfRefVue.setTrueBoutonCours();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                menuProfRefVue.setTrueBoutonCours();
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        setVisible(true);


    }
}
