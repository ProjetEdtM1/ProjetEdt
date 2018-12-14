package fr.utln.projet.vue;

import fr.utln.projet.controleur.CoursControleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ResourceBundle;

import static com.sun.glass.ui.Cursor.setVisible;

public class PlanningProfesseurVue extends Fenetre implements PlanningSuperVue {
    private CoursControleur coursControleur;
    private String login;
    private MenuProfSuperVue menuProfVue;

    private JLabel labelTitre;
    private JTable tablePlanning;
    private ResourceBundle rbMenu = ResourceBundle.getBundle("textMenu");

    private JMenuBar menuedt = new JMenuBar();
    private JMenu  menuCours = new JMenu(rbMenu.getString("Cours"));
    private JMenu  menuSalles = new JMenu(rbMenu.getString("Salles"));
    private JMenu  menuMateriel = new JMenu(rbMenu.getString("Materiel"));
    private JMenu  menuAffichage = new JMenu(rbMenu.getString("Affichage"));
    private JMenu  logout = new JMenu(rbMenu.getString("Logout"));

    public PlanningProfesseurVue(String login, final MenuProfSuperVue menuProfVue){
        super();
        setTitle("Planning");
        this.coursControleur = new CoursControleur(this);
        this.login = login;
        this.menuProfVue=menuProfVue;


        final Container contentPane = getContentPane();

        JPanel panelGeneral = new JPanel(new GridBagLayout());

        labelTitre = new JLabel("Planning");

        String[] entetes = {"Heure","Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche"};

        Object[][] donnes = this.coursControleur.remplireTableCoursProf(this.login);
        tablePlanning = new JTable(donnes,entetes);

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

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                menuProfVue.setTrueBoutonAffichagePlanning();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                menuProfVue.setTrueBoutonAffichagePlanning();
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

    }
}
