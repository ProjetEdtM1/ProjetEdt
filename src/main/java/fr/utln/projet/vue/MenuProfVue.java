package fr.utln.projet.vue;


import fr.utln.projet.modele.ReserverSalleModele;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuProfVue extends JFrame implements MenuProfSuperVue {
    private String loginProf;

    private  JButton boutonGererReservationSalle;
    private JButton boutonAffichagePlanning;

    private final Container contentPane = getContentPane();
    private JPanel panelGeneral = new JPanel();

    public MenuProfVue(final String loginProf){
        super("Menu professeur");
        this.loginProf = loginProf;
        setSize(300,400);
        boutonGererReservationSalle = new JButton("Réserver une salle");
        boutonAffichagePlanning = new JButton("Planning");


        boutonGererReservationSalle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReserverSalleVue(new ReserverSalleModele(),getInstance());
                boutonGererReservationSalle.setEnabled(false);
            }
        });

        boutonAffichagePlanning.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PlanningProfesseurVue(getInstance().loginProf,getInstance());
                boutonAffichagePlanning.setEnabled(false);
            }
        });

        panelGeneral.add(boutonGererReservationSalle);
        panelGeneral.add(boutonAffichagePlanning);
        contentPane.add(panelGeneral);

        setVisible(true);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void setTrueBoutonGererReservationSalle(){
        boutonGererReservationSalle.setEnabled(true);
    }


    public MenuProfVue getInstance(){return this;}

    @Override
    public void setTrueBoutonAffichagePlanning() {
        boutonAffichagePlanning.setEnabled(true);
    }
}
