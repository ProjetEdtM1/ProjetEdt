package fr.utln.projet.vue;

import fr.utln.projet.controleur.CoursControleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ResourceBundle;

public class PlanningEtudiantVue extends Fenetre {
    private CoursControleur coursControleur;
    private String intituleGroupe;

    private JLabel labelTitre;
    private JTable tablePlanning;
    private ResourceBundle rbMenu = ResourceBundle.getBundle("textMenu");

    private JMenuBar menuedt = new JMenuBar();
    private JMenu  menuCours = new JMenu(rbMenu.getString("Cours"));
    private JMenu  menuSalles = new JMenu(rbMenu.getString("Salles"));
    private JMenu  menuMateriel = new JMenu(rbMenu.getString("Materiel"));
    private JMenu  menuAffichage = new JMenu(rbMenu.getString("Affichage"));
    private JMenu  logout = new JMenu(rbMenu.getString("Logout"));

    public PlanningEtudiantVue(String intituleGroupe){
        super();
        setTitle("Planning");
        this.coursControleur = new CoursControleur(this);
        this.intituleGroupe = intituleGroupe;


        final Container contentPane = getContentPane();
        //JPanel panelGeneral = new JPanel();
        //ajout cyril
        JPanel panelGeneral = new JPanel(new GridBagLayout());

        labelTitre = new JLabel("Planning");

        String[] entetes = {"Heure","Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche"};
        /**Object[][] donnes={{"8h","","","","","","",""},{"9h","","","","","","",""},{"10h","","","","","","",""},{"11h","","","","","","",""},{"12h","","","","","","",""}
                ,{"13h","","","","","","",""},{"14h","","","","","","",""},{"15h","","","","","","",""},{"16h","","","","","","",""},{"17h","","","","","","",""},{"18h","","","","","","",""}};
**/
        Object[][] donnes = this.coursControleur.remplireTableCours(this.intituleGroupe);
        tablePlanning = new JTable(donnes,entetes);

       // panelGeneral.add(labelTitre,BorderLayout.NORTH);
         /*  panelGeneral.add(tablePlanning.getTableHeader(),BorderLayout.NORTH);
          panelGeneral.add(tablePlanning,BorderLayout.CENTER);*/

         //ajout par cyril
         GridBagConstraints c = new GridBagConstraints();
         c.gridx = 0;
         c.gridy = 0;
         panelGeneral.add(tablePlanning.getTableHeader(),c);
         c.gridy = 1;
        panelGeneral.add(tablePlanning,c);

        menuedt.add(menuCours);
        menuedt.add(menuSalles);
        menuedt.add(menuMateriel);
        menuedt.add(menuAffichage);
        contentPane.add(menuedt,BorderLayout.NORTH);


        contentPane.add(panelGeneral);
        setVisible(true);



    }

}
