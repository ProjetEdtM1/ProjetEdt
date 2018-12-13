package fr.utln.projet.vue;

import fr.utln.projet.modele.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

import static fr.utln.projet.vue.MaterielVue.langueListModele;

public class MenuProfRefVue extends Fenetre {

    // bundle pour l'internationalisation
    private ResourceBundle rbBouton = ResourceBundle.getBundle("textBouton");

    // Bouton
    private  JButton boutonGererModules;// = new JButton("Gérer modules");
    private  JButton boutonGererEtudiants;// = new JButton("Gérer étudiants");
    private  JButton boutonGererProf;
    private JButton boutonGererCours;
    private JButton boutonGererSalle;
    private JButton boutonGererMateriel;


    private JButton boutonImage = new JButton(new ImageIcon("/home/cclain594/Bureau/copie_git_23_11/projetEdt/code.gif"));

    public static LangueListModele langueListModele;
    private final JList<String> langueJlist;

    private final JPanel panelGeneral = new JPanel(new GridBagLayout());
    private final JPanel panelLangue = new JPanel();

    private final Container contentPane = getContentPane();



    private String loginProfRef;

    public MenuProfRefVue(String loginProfRef) {
        super();
        setTitle("Menu");

        this.loginProfRef = loginProfRef;

        boutonGererModules = new JButton(rbBouton.getString("Gerer modules"));
        boutonGererEtudiants = new JButton(rbBouton.getString("Gerer etudiants"));
        boutonGererProf = new JButton(rbBouton.getString("Gerer professeurs"));
        boutonGererCours = new JButton(rbBouton.getString("Gerer cours"));
        boutonGererSalle = new JButton(rbBouton.getString("Gerer salle"));
        boutonGererMateriel = new JButton(rbBouton.getString("Gerer materiel"));

        String[] listlangue = {"Francais","Anglais"};

        langueListModele = new LangueListModele(Arrays.asList(listlangue));
        langueJlist = new JList<>(langueListModele);
        langueJlist.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                changeBundle(langueJlist.getSelectedValue());
            }
        });

        /*boutonImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });*/


        //action lsitener empechant aux vue d'etre ouverte a la chaine si elle n'est pas fermé
        boutonGererMateriel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MaterielVue(new MaterielModele(),getInstance());
                boutonGererMateriel.setEnabled(false);
            }
        });

        boutonGererSalle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SalleVue(new SalleModele(),getInstance());
                boutonGererSalle.setEnabled(false);
            }
        });

        this.boutonGererModules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ModuleVUE(new ModuleModele(),getInstance());
                boutonGererModules.setEnabled(false);
            }
        });

        this.boutonGererEtudiants.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EtudiantVue(new ModeleEtudiant(),getInstance());
                boutonGererEtudiants.setEnabled(false);
            }
        });

        this.boutonGererProf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProfesseurVue(new ProfesseurModele(),getInstance());
                boutonGererProf.setEnabled(false);
            }
        });

        this.boutonGererCours.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CoursVue(new CoursModele(),getInstance());
                boutonGererCours.setEnabled(false);
            }
        });

        contentPane.setLayout(new BorderLayout());

        panelLangue.add(langueJlist);
        contentPane.add(panelLangue,BorderLayout.WEST);
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 2;
        c.gridwidth = 2;
        panelGeneral.add(boutonGererModules,c);
        c.gridx = 2;
        panelGeneral.add(boutonGererEtudiants,c);
        c.gridx = 4;
        panelGeneral.add(boutonGererProf,c);
        c.gridx = 6;
        panelGeneral.add(boutonGererSalle,c);
        c.gridx = 8;
        panelGeneral.add(boutonGererMateriel,c);



        contentPane.add(panelGeneral);// add(component) equals add(component,borderlayout.center)

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setVisible(true);

    }


    public MenuProfRefVue getInstance(){
        return this;
    }

    // rend clicable le bouton une fois la fentre associé fermé

    public void setTrueBoutonMa(){
        boutonGererSalle.setEnabled(true);
    }

    public void setTrueBoutonModule(){
        this.boutonGererModules.setEnabled(true);
    }

    public void setTrueBoutonEtudiant(){
        this.boutonGererEtudiants.setEnabled(true);
    }

    public void setTrueBoutonProf(){
        this.boutonGererProf.setEnabled(true);
    }

    public void setTrueBoutonCours(){this.boutonGererCours.setEnabled(true);}

    public void setTrueBoutonMateriel(){boutonGererMateriel.setEnabled(true);}


    private void changeBundle(String selectedValue) {
        if (selectedValue.compareTo("Anglais") == 0){
            rbBouton = ResourceBundle.getBundle("textBouton", Locale.ENGLISH);

        }
        else
            rbBouton = ResourceBundle.getBundle("textBouton",Locale.FRANCE);
        setTextBouton(rbBouton);

    }


    private void setTextBouton(ResourceBundle rbBouton) {
        boutonGererModules.setText(rbBouton.getString("Gerer modules"));
        boutonGererEtudiants.setText(rbBouton.getString("Gerer etudiants"));
        boutonGererProf.setText(rbBouton.getString("Gerer professeurs"));
        boutonGererSalle.setText(rbBouton.getString("Gerer salle"));
        boutonGererMateriel.setText(rbBouton.getString("Gerer materiel"));
    }
}