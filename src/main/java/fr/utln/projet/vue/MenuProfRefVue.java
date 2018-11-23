package fr.utln.projet.vue;

import fr.utln.projet.modele.ModeleEtudiant;
import fr.utln.projet.modele.ModuleModele;
import fr.utln.projet.modele.ProfesseurModele;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuProfRefVue extends JFrame {
    private  JButton boutonGererModules = new JButton("Gérer modules");
    private  JButton boutonGererEtudiants = new JButton("Gérer étudiants");
    private  JButton boutonGererProf = new JButton("Gérer professeurs");

    private String loginProfRef;

    public MenuProfRefVue(String loginProfRef){
        super("Menu");
        this.loginProfRef = loginProfRef;

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500,300);
        final Container contentPane = getContentPane();

        JPanel panelGeneral = new JPanel();
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

        contentPane.add(panelGeneral);

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
}