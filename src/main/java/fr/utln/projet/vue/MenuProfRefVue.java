package fr.utln.projet.vue;

import fr.utln.projet.modele.LangueListModele;
import fr.utln.projet.modele.ModeleEtudiant;
import fr.utln.projet.modele.ModuleModele;
import fr.utln.projet.modele.ProfesseurModele;

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
    private  JButton boutonGererProf;// = new JButton("Gérer professeurs");

    public static LangueListModele langueListModele;
    private final JList<String> langueJlist;

    private final JPanel panelGeneral = new JPanel();
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

        String[] listlangue = {"Francais","Anglais"};

        langueListModele = new LangueListModele(Arrays.asList(listlangue));
        langueJlist = new JList<>(langueListModele);
        langueJlist.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                changeBundle(langueJlist.getSelectedValue());
            }
        });


        panelGeneral.add(boutonGererModules);
        this.boutonGererModules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ModuleVUE(new ModuleModele(),getInstance());
                boutonGererModules.setEnabled(false);
            }
        });
        panelGeneral.add(boutonGererEtudiants);
        this.boutonGererEtudiants.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EtudiantVue(new ModeleEtudiant(),getInstance());
                boutonGererEtudiants.setEnabled(false);
            }
        });
        panelGeneral.add(boutonGererProf);
        this.boutonGererProf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProfesseurVue(new ProfesseurModele(),getInstance());
                boutonGererProf.setEnabled(false);
            }
        });

        contentPane.setLayout(new BorderLayout());

        panelLangue.add(langueJlist);
        contentPane.add(panelLangue,BorderLayout.WEST);
        contentPane.add(panelGeneral);// add(component) equals add(component,borderlayout.center)

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setVisible(true);

    }


    public MenuProfRefVue getInstance(){
        return this;
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

    private void changeBundle(String selectedValue) {
        System.out.println("je passe");
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
    }
}